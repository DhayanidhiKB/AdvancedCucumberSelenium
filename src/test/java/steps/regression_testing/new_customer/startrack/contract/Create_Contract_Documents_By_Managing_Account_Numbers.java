package steps.regression_testing.new_customer.startrack.contract;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import pojos.ContractConfirmationDetails;
import steps.Base;

public class Create_Contract_Documents_By_Managing_Account_Numbers {
    private final Base lBase;

    public Create_Contract_Documents_By_Managing_Account_Numbers(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} confirms details like key contact[{string} {string}] and creates contract documents from {string} proposal")
    public void create_contract_documents(@NotNull String user, String first_name, String last_name,
                                          String proposal_status, ContractConfirmationDetails details) {
        String user_name = "";
        String password = "";
        switch (user) {
            case "Onboarding":
                user_name = UserConfig.getProperties().onBoardingUsername();
                password = Utilities.decode(UserConfig.getProperties().onBoardingPassword());
                break;
            case "Sales":
                user_name = UserConfig.getProperties().salesUsername();
                password = Utilities.decode(UserConfig.getProperties().salesPassword());
                break;
        }

        this.lBase.salesforce.visit(UserConfig.getProperties().appUrl())
                .getLoginPage()
                .is_ready().login_as(user_name, password)
                .getHeader().is_ready().search(this.lBase.proposalName)
                .getAppNavigator()
                .is_ready()
                .getProposalPage()
                .is_ready().verify_status(proposal_status, first_name + " " + last_name)
                .getCreateContractTypes()
                .create_contract(proposal_status)
                .getConfirmContractDetails()
                .is_ready().contract_entity_is(details.getEntity())
                .key_contact_is(first_name + " " + last_name)
                .contract_type_is(details.getType())
                .included_product(details.getProduct()).next();

        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails().getManageLodgementPointsAndAccountNumbersPage()
                .getContractHeader()
                .is_ready()
                .getContractPage()
                .create_contract().create_contract_documents();

        this.lBase.contractName = this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes().getConfirmContractDetails()
                .getManageLodgementPointsAndAccountNumbersPage()
                .getContractHeader()
                .is_ready().ready_to_sign().get_name();

        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();
    }
}
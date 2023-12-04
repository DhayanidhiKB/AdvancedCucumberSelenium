package steps.regression_testing.new_customer.appc.contract;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import pojos.Contract;
import steps.Base;

public class Manually_Sign_The_Contract {
    private final Base lBase;

    public Manually_Sign_The_Contract(@NotNull Base base) {
        this.lBase = base;
    }

    @When("Onboarding user, manually sign the contract[{int}] under {string} and confirming its status as {string}")
    public void manually_sign_the_contract(int index, String opp_name,
                                           String status, @NotNull Contract contract) {
        this.lBase.login_into_salesforce_as(UserConfig.getProperties().onBoardingUsername(),
                Utilities.decode(UserConfig.getProperties().onBoardingPassword()));

        this.lBase.salesforce.getLoginPage().getHeader()
                .search(opp_name)
                .getAppNavigator().is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().products_contracts()
                .getProductsAndContracts()
                .is_ready().open_contract(index);

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes()
                .getConfirmContractDetails().getManageContractRelationshipsPage()
                .getContractHeader()
                .is_ready().verify_contract_details(opp_name, this.lBase.proposalName)
                .getContractPage()
                .signing_the_contract(contract.getSign_step(), contract.getSign_status(),
                        contract.getApproval_status())
                .verify(contract.getSign_step(), status)
                .activate_the_contract(contract.getActive_step(), contract.getActive_status())
                .verify(contract.getActive_step(), contract.getActive_status());
    }
}
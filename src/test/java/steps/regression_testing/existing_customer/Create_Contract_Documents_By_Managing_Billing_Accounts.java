package steps.regression_testing.existing_customer;

import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import pojos.ContractConfirmationDetails;
import steps.Base;

public class Create_Contract_Documents_By_Managing_Billing_Accounts {
    private final Base lBase;

    public Create_Contract_Documents_By_Managing_Billing_Accounts(@NotNull Base base) {
        this.lBase = base;
    }

    @When("Creates contract from {string} proposal by confirming details like key contact[{string} {string}] and managing billing accounts for {string}")
    public void create_contract_documents(String proposal_status, String first_name, String last_name, String product,
                                          @NotNull ContractConfirmationDetails details) {
        this.lBase.salesforce
                .getLoginPage()
                .getHeader()
                .is_ready().search(this.lBase.proposalName)
                .getAppNavigator()
                .is_ready()
                .getProposalPage()
                .is_ready().verify_status(proposal_status, first_name + " " + last_name)
                .getCreateContractTypes()
                .create_contract(proposal_status)
                .getConfirmContractDetails()
                .is_ready()
                .contract_entity_is(details.getEntity())
                .key_contact_is(first_name + " " + last_name)
                .contract_type_is(details.getType())
                .included_product(details.getProduct()).next()
                .getManageLodgementPointsAndAccountNumbersPage()
                .is_ready()
                .getManageAccountNumber()
                .manage_billing_account_for(product).back_to_contract();

        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails().getManageLodgementPointsAndAccountNumbersPage()
                .getContractHeader()
                .is_ready()
                .getContractPage()
                .create_contract()
                .create_contract_documents();

        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails().getManageLodgementPointsAndAccountNumbersPage()
                .getContractHeader()
                .is_ready().ready_to_sign();

        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();
    }
}
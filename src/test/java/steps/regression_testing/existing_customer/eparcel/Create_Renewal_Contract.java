package steps.regression_testing.existing_customer.eparcel;

import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import pojos.ContractConfirmationDetails;
import steps.Base;

public class Create_Renewal_Contract {
    private final Base lBase;

    public Create_Renewal_Contract(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} documents for[{string}, {string}] by managing {string} billing accounts and confirming details like key contact[{string} {string}]")
    public void create_renewal_contract(String renew_contract, String contract_condition,
                                        String term, String product,
                                        String first_name, String last_name,
                                        @NotNull ContractConfirmationDetails details) {
        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes()
                .contract_renewal(renew_contract)
                .getConfirmContractDetails()
                .is_ready()
                .contract_entity_is(details.getEntity())
                .key_contact_is(first_name + " " + last_name)
                .contract_type_is(details.getType())
                .included_product(details.getProduct())
                .set_contract_condition_as(contract_condition)
                .enter_term(term).next()
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
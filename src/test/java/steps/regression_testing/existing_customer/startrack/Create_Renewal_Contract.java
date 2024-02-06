package steps.regression_testing.existing_customer.startrack;

import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import pojos.ContractConfirmationDetails;
import steps.Base;

public class Create_Renewal_Contract {
    private final Base lBase;

    public Create_Renewal_Contract(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} documents for[{string}, {string}] by confirming details like key contact[{string} {string}]")
    public void create_renewal_contract(String renew_contract, String contract_condition,
                                        String term, String first_name, String last_name,
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
                .enter_term(term).next();

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
                .ready_to_sign().get_name();

        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();
    }
}
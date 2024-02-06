package steps.regression_testing.existing_customer.startrack;

import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import pojos.ContractConfirmationDetails;
import steps.Base;

public class Amend_The_Contract {
    private final Base lBase;

    public Amend_The_Contract(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("Amends the contract by adding dov language[{string}, {string}, {string}], confirming details like key contact[{string} {string}] and creates contract documents")
    public void amend_the_contract(String dov_type, String dov_reason, String dov_product,
                                   String first_name, String last_name,
                                   @NotNull ContractConfirmationDetails details) {
        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes()
                .contract_amendment()
                .getConfirmContractDetails()
                .is_ready()
                .contract_entity_is(details.getEntity())
                .key_contact_is(first_name + " " + last_name)
                .contract_type_is(details.getType())
                .included_product(details.getProduct()).next();

        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails().getManageLodgementPointsAndAccountNumbersPage()
                .getContractHeader()
                .is_ready()
                .getContractPage()
                .getContractRelatedLinks()
                .open_dov_language()
                .getDovLanguages()
                .is_ready().new_dov_language(dov_type, dov_reason, dov_product)
                .verify_dov_language().is_ready().back_to_contract();

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
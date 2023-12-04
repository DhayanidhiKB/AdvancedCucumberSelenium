package steps.regression_testing.new_customer.eparcel.contract;

import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import pojos.ContractConfirmationDetails;
import steps.Base;

public class Create_Contract_Documents_By_Managing_Charge_Accounts {
    private final Base lBase;

    public Create_Contract_Documents_By_Managing_Charge_Accounts(@NotNull Base base) {
        this.lBase = base;
    }

    @When("Creates contract from {string} proposal by confirming details like key contact[{string} {string}] and managing account numbers")
    public void create_contract_documents(String proposal_status, String first_name, String last_name,
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
                .is_ready();

        if (details.getProduct().contains("eParcels")) {
            this.lBase.salesforce
                    .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                    .getCreateContractTypes().getConfirmContractDetails()
                    .getManageLodgementPointsAndAccountNumbersPage().getManageAccountNumber()
                    .manage_charge_account_for("eParcel").back_to_contract();
            this.lBase.click_on_eParcel_create_contract();
            this.lBase.salesforce
                    .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                    .getCreateContractTypes().getConfirmContractDetails()
                    .getManageLodgementPointsAndAccountNumbersPage()
                    .is_ready()
                    .getManageAccountNumber()
                    .manage_charge_account_for("eParcel Express").back_to_contract();
        } else {
            this.lBase.salesforce
                    .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                    .getCreateContractTypes().getConfirmContractDetails()
                    .getManageLodgementPointsAndAccountNumbersPage().getManageAccountNumber()
                    .manage_account_number().back_to_contract();
        }
        this.lBase.click_on_eParcel_create_contract();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails()
                .getManageLodgementPointsAndAccountNumbersPage()
                .getContractHeader().getContractPage()
                .create_contract_documents();

        this.lBase.eParcel_contract_is_ready_to_sign();
        this.lBase.log_off_from_salesforce();
    }
}
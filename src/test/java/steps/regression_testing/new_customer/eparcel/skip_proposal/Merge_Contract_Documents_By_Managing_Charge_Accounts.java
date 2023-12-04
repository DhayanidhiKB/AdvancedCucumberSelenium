package steps.regression_testing.new_customer.eparcel.skip_proposal;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Merge_Contract_Documents_By_Managing_Charge_Accounts {
    private final Base lBase;

    public Merge_Contract_Documents_By_Managing_Charge_Accounts(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} {string} that contains[{string}, {string}] is changed to " +
            "[{string}, {string}] by adding task[{string}, {string}] and " +
            "merge {string} with contract after managing account numbers")
    public void create_contract_documents(String opp_stage, String opp_name, String proposal_status,
                                          String key_contact, String next_opp_stage, String next_step,
                                          String subject, String contact, String file_name) {
        this.lBase.salesforce.getLoginPage().getHeader()
                .is_ready().search(this.lBase.proposalName)
                .getAppNavigator()
                .is_ready()
                .getProposalPage()
                .is_ready().verify_status(proposal_status, key_contact);

        this.lBase.log_off_from_salesforce();
        this.lBase.login_into_salesforce_as(UserConfig.getProperties().onBoardingUsername(),
                Utilities.decode(UserConfig.getProperties().onBoardingPassword()));

        this.lBase.salesforce.getLoginPage().getHeader()
                .search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .opportunity_stage_is(opp_stage)
                .getOpportunitySubHeader()
                .activity(subject, contact)
                .getOpportunityTabSet()
                .is_ready()
                .getDetails()
                .is_ready().change_stage_to(next_opp_stage, next_step).save_changes();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).opportunity_stage_is(next_opp_stage)
                .getOpportunitySubHeader()
                .scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().products_contracts()
                .getProductsAndContracts()
                .is_ready().open_contract(0);

        this.lBase.click_on_eParcel_create_contract();
        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails()
                .getManageLodgementPointsAndAccountNumbersPage()
                .is_ready()
                .getManageAccountNumber()
                .manage_charge_account_for("eParcel").back_to_contract();

        this.lBase.click_on_eParcel_create_contract();
        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails()
                .getManageLodgementPointsAndAccountNumbersPage()
                .is_ready()
                .getManageAccountNumber()
                .manage_charge_account_for("eParcel Express").back_to_contract();

        this.lBase.click_on_eParcel_create_contract();
        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails().getManageLodgementPointsAndAccountNumbersPage()
                .getContractHeader().getContractPage()
                .create_contract_documents();

        this.lBase.eParcel_contract_is_ready_to_sign();
        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes()
                .getConfirmContractDetails().getManageContractRelationshipsPage()
                .getContractHeader()
                .is_ready().upload_and_merge(UserConfig.getProperties().uploads() + file_name)
                .getContractPage()
                .merge_documents();
        this.lBase.log_off_from_salesforce();
    }
}
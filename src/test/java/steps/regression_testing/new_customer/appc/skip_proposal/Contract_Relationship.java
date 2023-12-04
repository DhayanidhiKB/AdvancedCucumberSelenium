package steps.regression_testing.new_customer.appc.skip_proposal;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Contract_Relationship {
    private final Base lBase;

    public Contract_Relationship(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} {string} that contains[{string}, {string}] is changed to [{string}, {string}] " +
            "by adding task[{string}, {string}] and contract relationship is applied at[{string}, {string}, {string}] by {string} user")
    public void contract_relationship(String opp_stage, String opp_name, String proposal_status,
                                      String key_contact, String next_opp_stage, String next_step,
                                      String subject, String contact, String relationship,
                                      String level, String lodgement_point, @NotNull String user) {
        this.lBase.salesforce
                .getLoginPage()
                .getHeader()
                .is_ready().search(this.lBase.proposalName)
                .getAppNavigator()
                .is_ready()
                .getProposalPage()
                .is_ready().verify_status(proposal_status, key_contact);

        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();

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

        this.lBase.salesforce
                .visit(UserConfig.getProperties().appUrl())
                .getLoginPage()
                .is_ready().login_as(user_name, password)
                .getHeader()
                .is_ready().search(opp_name)
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

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes()
                .getConfirmContractDetails().getManageContractRelationshipsPage()
                .getContractHeader()
                .is_ready()
                .getContractPage()
                .create_contract();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes()
                .getConfirmContractDetails().getManageContractRelationshipsPage()
                .is_ready().add(relationship, level).select_required(relationship, lodgement_point);

        if (!level.contains("Organisation")) {
            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                    .getProposalPage().getCreateContractTypes()
                    .getConfirmContractDetails()
                    .getManageContractRelationshipsPage()
                    .select_charge_account().select_sub_account();
        }

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes()
                .getConfirmContractDetails()
                .getManageContractRelationshipsPage()
                .apply_relationship().verify_added_billing_account(level).back_to_contract();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes()
                .getConfirmContractDetails().getManageContractRelationshipsPage()
                .getContractHeader()
                .is_ready()
                .getContractPage()
                .refresh_and_wait().create_contract();

        this.lBase.contractName = this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes()
                .getConfirmContractDetails().getManageContractRelationshipsPage()
                .getContractHeader()
                .is_ready().ready_to_sign().get_name();
        System.out.println("Contract Name:" + this.lBase.contractName);

        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();
    }
}
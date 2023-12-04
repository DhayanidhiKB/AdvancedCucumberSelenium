package steps.regression_testing.new_customer.appc.opportunity;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import pojos.SubAccountRequest;
import steps.Base;

public class Credit_Assessment_and_SubAccount {
    private final Base lBase;

    public Credit_Assessment_and_SubAccount(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} user tries to create contract from an {string} Proposal lands in {string} page " +
            "for to complete credit assessment using abn[{string}] for {string} " +
            "by entering {string}, {string}, {string}, {string}, creates sub account [{string}, {string}] " +
            "for {string} with below details and change stage from {string} to [{string}, {string}] " +
            "by adding task[{string}, {string}]")
    public void credit_assessment(@NotNull String user, String proposal_stage, String opp_name, String abn,
                                  @NotNull String account_type, String industry, String email, String street_number,
                                  String sender, String sub_account_name, String sub_account_name2, String org_name,
                                  String current_opp_stage, String next_opp_stage, String next_step, String subject,
                                  String contact, @NotNull SubAccountRequest subAccountRequest) {
        this.lBase.salesforce.getLoginPage().getHeader()
                .getAppNavigator().getProposalPage()
                .getCreateContractTypes()
                .create_contract(proposal_stage)
                .getCompleteCreditAssessment()
                .is_displayed().accepted();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities()
                .getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().credit_assessment()
                .getCreditAssessmentPage()
                .is_it_ready().choose(account_type)
                .getSearchOrganisationPage()
                .is_it_ready().search(abn).select(abn)
                .credit_assess_using(sender, email, street_number, industry).save_assessment()/*.switch_to_default_content()*/;

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities()
                .getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                .getCreditAssessmentPage().getSearchOrganisationPage()
                .getNewSubAccountRequestPage()
                .is_ready().finalize(this.lBase.proposalName, org_name,
                        sub_account_name + "_" + Utilities.epochSeconds(),
                        sub_account_name2 + "_" + Utilities.epochSeconds(),
                        subAccountRequest.getParcel_send(), sender, subAccountRequest.getELMS(),
                        subAccountRequest.getAddress_line_1(), subAccountRequest.getAddress_line_2(),
                        subAccountRequest.getSub_urb(), subAccountRequest.getState(),
                        subAccountRequest.getPost_code(), subAccountRequest.getLodgement_point());

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities().getOpportunityHeader()
                .is_ready(opp_name);

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
        this.lBase.salesforce.visit(UserConfig.getProperties().appUrl())
                .getLoginPage()
                .is_ready()
                .login_as(user_name, password)
                .getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .verify_outstanding_actions(current_opp_stage).activity(subject, contact)
                .getOpportunityTabSet()
                .is_ready().credit_assessment()
                .getCreditAssessmentPage()
                .is_it_ready().verify_assessment_status();

        this.lBase.salesforce.getLoginPage().getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .scroll_into_view()
                .getOpportunityTabSet()
                .is_ready()
                .getDetails()
                .is_ready().change_stage_to(next_opp_stage, next_step).save_changes();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .is_ready().verify_outstanding_actions(next_opp_stage);
    }
}
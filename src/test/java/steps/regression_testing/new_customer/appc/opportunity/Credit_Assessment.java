package steps.regression_testing.new_customer.appc.opportunity;

import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Credit_Assessment {
    private final Base lBase;

    public Credit_Assessment(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} user tries to create contract from an {string} Proposal lands in {string} page " +
            "for to complete credit assessment using abn[{string}] for {string} " +
            "by entering {string}, {string}, {string}, {string} and change stage from {string} to " +
            "[{string}, {string}] by adding task[{string}, {string}]")
    public void credit_assessment(@NotNull String user, String proposal_stage, String opp_name, String abn,
                                  @NotNull String account_type, String industry, String email, String street_number,
                                  String sender, String current_opp_stage, String next_opp_stage, String next_step,
                                  String subject, String contact) {
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
                .credit_assess_using(sender, email, street_number, industry)
                .save_assessment();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities().getOpportunityHeader()
                .is_ready(opp_name);

        this.lBase.log_off_from_salesforce();
        this.lBase.login_into_salesforce_as(user);

        this.lBase.salesforce.getLoginPage().getHeader()
                .search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .verify_outstanding_actions(current_opp_stage)
                .activity(subject, contact)
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
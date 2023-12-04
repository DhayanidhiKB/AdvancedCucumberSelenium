package steps.regression_testing.user_stories;

import io.cucumber.java.en.Then;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Customer_Disagree_CreditAssessment {
    private final Base lBase;

    public Customer_Disagree_CreditAssessment(@NotNull Base base) {
        this.lBase = base;
    }

    @Then("{string} credit assessment for {string} returns to opportunity[{string}] page " +
            "in [{string}, {string}] stage with task[{string}, {string} {string}]")
    public void customer_disagree(String action, @NotNull String account_type, String opp_name,
                                  String opp_stage, String next_step, String subject,
                                  String first_name, String last_name) {
        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities()
                .getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .is_ready()
                .activity(subject, first_name + " " + last_name);

        this.lBase.salesforce.getLoginPage().getHeader()
                .refresh().is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .scroll_into_view()
                .getOpportunityTabSet()
                .is_ready()
                .getDetails()
                .is_ready().change_stage_to(opp_stage, next_step).save_changes();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities()
                .getOpportunityHeader()
                .is_ready(opp_name).opportunity_stage_is(opp_stage)
                .getOpportunitySubHeader()
                .is_ready().scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().credit_assessment()
                .getCreditAssessmentPage()
                .is_it_ready().choose(account_type)
                .getSearchOrganisationPage()
                .is_it_ready().action(action);

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities()
                .getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions();
    }
}
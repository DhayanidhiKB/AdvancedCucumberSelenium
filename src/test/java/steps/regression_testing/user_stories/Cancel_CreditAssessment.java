package steps.regression_testing.user_stories;

import io.cucumber.java.en.Then;
import org.jetbrains.annotations.NotNull;
import pojos.Competitor;
import steps.Base;

public class Cancel_CreditAssessment {
    private final Base lBase;

    public Cancel_CreditAssessment(@NotNull Base base) {
        this.lBase = base;
    }

    @Then("{string} credit assessment for {string} returns to opportunity[{string}] page in [{string},{string}] stage with below competitor")
    public void cancel(String action, @NotNull String account_type, String opp_name,
                                  String opp_stage, String next_step, @NotNull Competitor competitor) {
        this.lBase.salesforce.getLoginPage().getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .is_ready().scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().related()
                .getRelated()
                .new_competitor(competitor.getType(), competitor.getProduct(),
                        competitor.getName(), competitor.getStatus(), competitor.getAdvantage());

        this.lBase.salesforce
                .getLoginPage().getHeader()
                .refresh().is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .is_ready().scroll_into_view()
                .getOpportunityTabSet()
                .is_ready()
                .getDetails()
                .is_ready().change_stage_to(opp_stage, next_step)
                .add("Status Update", next_step).save_changes();

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
package steps.regression_testing.new_customer;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import pojos.Opportunity;
import steps.Base;

public class Create_Opportunity {
    private final Base lBase;

    public Create_Opportunity(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("Sales user, created an opportunity[{string}] for the above organisation[{string}] " +
            "with key contact[{string} {string}], post code {string}, monthly spend {string} and changed its stage to {string}")
    public void opportunities(String opp_name, String org_name, String first_name, String last_name,
                              String post_code, String monthly_spend, String opp_stage,
                              @NotNull Opportunity opportunity) {
        this.lBase.login_into_salesforce_as(UserConfig.getProperties().salesUsername(),
                Utilities.decode(UserConfig.getProperties().salesPassword()));

        this.lBase.salesforce.getLoginPage().getHeader()
                .getAppNavigator()
                .is_ready().set("AP Sales").is_ready().opportunities()
                .getOpportunities()
                .new_opportunity(opportunity.getSub_type(), opportunity.getStage(), opportunity.getIdentify_steps(),
                        opp_name, org_name, first_name, last_name, opportunity.getClose_date(),
                        opportunity.getIs_it_startrack(), post_code, monthly_spend,
                        opportunity.getTotal_value(), opportunity.getType())
                .getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .is_ready().verify_outstanding_actions(opportunity.getStage()).scroll_into_view()
                .getOpportunityTabSet()
                .is_ready()
                .getDetails()
                .is_ready().change_stage_to(opp_stage, opportunity.getQualify_steps())
                .add("Description", opportunity.getDescription()).save_changes();

       /* this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities()
                .getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .is_ready().verify_outstanding_actions(opp_stage).scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().credit_assessment()
                .getCreditAssessmentPage()
                .is_it_ready().primary_proposal_is_required();*/
    }
}
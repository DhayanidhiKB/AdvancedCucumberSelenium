package steps.regression_testing.new_customer;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import pojos.Competitor;
import steps.Base;

public class Propose_Opportunity {
    private final Base lBase;

    public Propose_Opportunity(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("Sales user {string} the {string} for {string} after " +
            "adding competitors and status")
    public void propose_opportunity(String opp_stage, String opp_name,
                                    String next_step, @NotNull Competitor competitor) {
        this.lBase.log_off_from_salesforce();
        this.lBase.login_into_salesforce_as(UserConfig.getProperties().salesUsername(),
                Utilities.decode(UserConfig.getProperties().salesPassword()));

        this.lBase.salesforce.getLoginPage().getHeader()
                .search(opp_name)
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
                .is_ready().search(opp_name)
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
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .is_ready().verify_outstanding_actions(opp_stage);

        this.lBase.log_off_from_salesforce();
    }
}
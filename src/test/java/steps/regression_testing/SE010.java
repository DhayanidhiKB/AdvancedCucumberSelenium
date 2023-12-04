package steps.regression_testing;

import io.cucumber.java.en.Then;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class SE010 extends Base {
    private final Base lBase;

    public SE010(@NotNull Base base) {
        this.lBase = base;
    }

    @Then("User is able to {string} or {string} from {string}")
    public void new_or_move(String user_action1, String user_action2, String opp_name) {
        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities()
                .getOpportunityHeader()
                .is_ready(opp_name)
                .getOpportunitySubHeader()
                .is_ready().scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().credit_assessment()
                .getCreditAssessmentPage()
                .is_it_ready()
                .getMoveOrCreateNewCreditAssessment()
                .is_ready().action(user_action1).is_ready().action(user_action2).is_ready();
    }
}
package steps.regression_testing.user_stories;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Then;
import org.jetbrains.annotations.NotNull;
import pojos.ClosedOpportunity;
import steps.Base;

public class Close_Opportunity {
    private final Base lBase;

    public Close_Opportunity(@NotNull Base base) {
        this.lBase = base;
    }

    @Then("{string} user verifies message under credit assessment when opportunity[{string}] " +
            "is closed")
    public void closed_opportunity(@NotNull String user, String opp_name, @NotNull ClosedOpportunity details) {
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
                .is_ready()
                .search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities()
                .getOpportunityHeader()
                .close_opportunity(details.getStage_name(), details.getNext_steps(),
                        details.getReason(), details.getComment()).is_ready(opp_name)
                .getOpportunitySubHeader()
                .when_opportunity_is_closed(details.getStage_name())
                .getOpportunityTabSet()
                .is_ready().credit_assessment()
                .getCreditAssessmentPage()
                .is_it_ready().for_closed_opportunity();
    }
}
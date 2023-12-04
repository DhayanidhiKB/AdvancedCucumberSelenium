package steps.regression_testing.new_customer.startrack;

import com.my.salesforce.sandbox.Constants;
import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Then;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Offline_CheckOut {
    private final Base lBase;

    public Offline_CheckOut(@NotNull Base base) {
        this.lBase = base;
    }

    @Then("From {string} {string} user {string} {string} due to [{string}, {string}] after " +
            "validating {string}, {string}, {string} and {string}")
    public void offline_checkout(String opp_name, @NotNull String user, String checkout_option,
                                 String product, String reason, String comment, String service_type,
                                 String primary_suburb, String suburb, String cart_tier) {
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
                .is_ready().login_as(user_name, password)
                .getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).add_pricing_product()
                .getAllProducts()
                .is_ready().add_to_cart(product).configure(product)
                .getStarTrackProductAttributesPage()
                .enter_and_validate(product, service_type, primary_suburb, cart_tier, suburb)
                .review()
                .getCartActions()
                .please(checkout_option, reason, comment);

        this.lBase.salesforce.getLoginPage().getHeader()
                .getAppNavigator().getProposalPage()
                .is_ready().verify_stage(Constants.PROPOSAL_STAGE);
    }
}
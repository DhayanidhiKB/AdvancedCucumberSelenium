package steps.regression_testing.new_customer.startrack.skip_proposal;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Save_Cart_And_Edit_Opportunity_Products {
    private final Base lBase;

    public Save_Cart_And_Edit_Opportunity_Products(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("{string} cart saved with {string} after validating {string}, {string}, {string} and {string} by {string} user")
    public void save_cart_and_edit_products(String opp_name, String product, String service_type,
                                            String primary_suburb, String suburb, String cart_tier, @NotNull String user) {
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
                .is_ready(opp_name)
                .verify_opportunity_actions()
                .add_pricing_product()
                .getAllProducts()
                .is_ready()
                .add_to_cart(product)
                .still_not_configured(product)
                .getStarTrackProductAttributesPage()
                .enter_and_validate(product, service_type, primary_suburb, cart_tier, suburb)
                .review()
                 .getCartActions()
                .please("Save Cart & Bulk Edit Opportunity Products", "", "")
                .getEditProducts()
                .is_ready().edit_quantity("10").edit_unit_sales("100").save();

        this.lBase.salesforce.getLoginPage().getHeader()
                .search(opp_name)
                .getAppNavigator()
                .is_ready();
    }
}
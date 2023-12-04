package steps.regression_testing.new_customer.startrack.proposal;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.Constants;
import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import steps.Base;

import java.time.Duration;

public class Create_Proposal_And_CheckOut_StarTrack_Products {
    private final Base lBase;

    public Create_Proposal_And_CheckOut_StarTrack_Products(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("From {string} {string} user {string} {string} after validating " +
            "its attributes {string}, {string}, {string} and {string}")
    public void startrack_checkout(String opp_name, @NotNull String user,
                                   String checkout_option, String product,
                                   String service_type, String primary_suburb,
                                   String suburb, String cart_tier) {
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
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).add_pricing_product()
                .getAllProducts()
                .is_ready().add_to_cart(product).still_not_configured(product)
                .getStarTrackProductAttributesPage()
                .enter_and_validate(product, service_type, primary_suburb, cart_tier, suburb)
                .review().checkOut(product)
                .getCartActions().getCheckOut()
                .is_ready().select(checkout_option).switch_to_default_content();

        switch (checkout_option) {
            case "Checkout Only":
                this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                        .getOpportunities().getOpportunityHeader()
                        .getOpportunitySubHeader()
                        .is_ready().scroll_into_view()
                        .getOpportunityTabSet()
                        .is_ready().products_contracts()
                        .getProductsAndContracts()
                        .is_ready().edit_products()
                        .getEditProducts()
                        .is_ready().edit_quantity("10").edit_unit_sales("100").save();

                this.lBase.salesforce.getLoginPage().getHeader()
                        .is_ready().search(opp_name)
                        .getAppNavigator()
                        .getOpportunities().getOpportunityHeader()
                        .is_ready(opp_name);
                break;
            case "Generate Proposal Document":
                this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                        .getOpportunities().getOpportunityHeader().getAllProducts()
                        .getEParcelProductAttributesPage().getCartActions().getCheckOut()
                        .getEditProducts()
                        .is_ready().confirm_opportunity_products();
                Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(150));
                this.lBase.salesforce.getLoginPage().getHeader()
                        .getAppNavigator().getProposalPage()
                        .is_ready().verify_stage(Constants.PROPOSAL_STAGE);
                this.lBase.salesforce.getLoginPage().getHeader()
                        .is_ready().search(opp_name)
                        .getAppNavigator()
                        .getOpportunities().getOpportunityHeader()
                        .is_ready(opp_name).getOpportunitySubHeader()
                        .is_ready().scroll_into_view()
                        .getOpportunityTabSet()
                        .is_ready().products_contracts()
                        .getProductsAndContracts()
                        .is_ready().edit_products()
                        .getEditProducts()
                        .is_ready().edit_quantity("10").edit_unit_sales("100").save();
                break;
        }
    }
}
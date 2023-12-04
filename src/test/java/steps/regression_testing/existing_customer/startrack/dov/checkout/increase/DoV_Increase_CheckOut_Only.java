package steps.regression_testing.existing_customer.startrack.dov.checkout.increase;

import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class DoV_Increase_CheckOut_Only {
    private final Base lBase;

    public DoV_Increase_CheckOut_Only(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} after adding {string} to the dov opportunity[{string}] by validating its attributes {string}, {string}, {string}, {string}")
    public void checkout_process(String check_out_option, String product, String opp_name,
                                 String attribute_1, String attribute_2, String attribute_3,
                                 String attribute_4) {
        this.lBase.salesforce.getLoginPage().getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .add_pricing_product()
                .getAllProducts()
                .is_ready().add_to_cart(product).configure(product)
                .getStarTrackProductAttributesPage()
                .enter_and_validate(product, attribute_1, attribute_2, attribute_3, attribute_4)
                .review().refresh_pricing()
                .getCartActions()
                .toggle_and_check_out()
                .getCheckOut()
                .is_ready().select(check_out_option).switch_to_default_content();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions();

        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();
    }
}
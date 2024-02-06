package steps.regression_testing.existing_customer.eparcel.renewal.checkout;

import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class CheckOut_Only {
    private final Base lBase;

    public CheckOut_Only(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} after adding {string} with rk code[{string}] to the dov opportunity[{string}] by validating " +
            "its attributes {string}, {string}, {string}, {string} and lodgement point {string}")
    public void check_out_only(String check_out_option, String product, String rk_code,
                               String opp_name, String attribute_1, String attribute_2,
                               String attribute_3, String attribute_4, String lodgement_point) {
        this.lBase.salesforce.getLoginPage().getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .add_pricing_product()
                .getAllProducts()
                .is_ready().add_to_cart(product).configure(product)
                .getEParcelProductAttributesPage()
                .is_ready().enter_and_validate(product, attribute_1, attribute_2, attribute_3, attribute_4)
                .review_cart().add_lodgementPoint(product, lodgement_point)
                .back_to_shopping_cart(product).enter_rk_code(rk_code)
                .submit_for_approval(product)
                .getCartActions()
                .check_out()
                .getCheckOut()
                .is_ready().select(check_out_option).switch_to_default_content();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions();

        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();
    }
}
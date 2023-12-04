package steps.regression_testing.existing_customer.startrack.dov.checkout.decrease;

import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class DoV_Decrease_CheckOut_Only {
    private final Base lBase;

    public DoV_Decrease_CheckOut_Only(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} after deleting {string} from {string}")
    public void dov_decrease_checkout_process(String check_out_option, String product, String opp_name) {
        this.lBase.salesforce.getLoginPage().getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .add_pricing_product()
                .getAllProducts()
                .is_ready().review()
                .getStarTrackProductAttributesPage().getCartActions()
                .delete(product).toggle_and_check_out()
                .getCheckOut()
                .is_ready().select(check_out_option).switch_to_default_content();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions();

        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();
    }
}
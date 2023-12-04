package steps.regression_testing.new_customer.eparcel;

import com.my.salesforce.sandbox.Constants;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Offline_CheckOut {
    private final Base lBase;

    public Offline_CheckOut(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("From {string} {string} user {string} {string} due to [{string}, {string}] after" +
            " validating its attributes {string}, {string}, {string}, {string} and adding " +
            "lodgement point {string}")
    public void offline_checkout(String opp_name, @NotNull String user, String checkout_option,
                                 String product, String reason, String comment, String product_industry,
                                 String domestic_customer, String revenue_commitment,
                                 String integration_platforms, String lodgement_point) {
        this.lBase.log_off_from_salesforce();
        this.lBase.login_into_salesforce_as(user);

        this.lBase.salesforce.getLoginPage().getHeader()
                .search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).add_pricing_product()
                .getAllProducts()
                .is_ready().add_to_cart(product).still_not_configured(product)
                .getEParcelProductAttributesPage()
                .is_ready().enter_and_validate(product, product_industry, domestic_customer,
                        revenue_commitment, integration_platforms)
                .review_cart().add_lodgementPoint(product, lodgement_point)
                .back_to_shopping_cart(product)
                .getCartActions()
                .please(checkout_option, reason, comment);

        this.lBase.salesforce.getLoginPage().getHeader()
                .getAppNavigator().getProposalPage()
                .is_ready().verify_stage(Constants.PROPOSAL_STAGE);
    }
}
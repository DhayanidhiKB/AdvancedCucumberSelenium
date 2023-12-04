package steps.regression_testing.new_customer.eparcel;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Saves_The_Cart {
    private final Base lBase;

    public Saves_The_Cart(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("{string} user saves the cart with {string} after validating " +
            "{string}, {string}, {string}, {string} and adding lodgement point {string}")
    public void save_cart_and_edit_products(String product, String product_industry,
                                            String domestic_customer, String revenue_commitment,
                                            String integration_platforms, String lodgement_point) {
        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .add_pricing_product()
                .getAllProducts()
                .is_ready()
                .add_to_cart(product)
                .still_not_configured(product)
                .getEParcelProductAttributesPage()
                .is_ready().enter_and_validate(product, product_industry, domestic_customer,
                        revenue_commitment, integration_platforms)
                .review_cart().add_lodgementPoint(product, lodgement_point)
                .getCartActions()
                .please("Save Cart & Bulk Edit Opportunity Products", "", "")
                .getEditProducts()
                .is_ready();
    }
}
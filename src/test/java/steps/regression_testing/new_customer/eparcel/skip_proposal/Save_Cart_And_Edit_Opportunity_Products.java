package steps.regression_testing.new_customer.eparcel.skip_proposal;

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

    @Given("{string} cart saved with {string} after validating {string}, {string}, " +
            "{string}, {string} and adding lodgement point {string} by {string} user")
    public void save_cart_and_edit_products(String opp_name, String product, String product_industry,
                                            String domestic_customer, String revenue_commitment,
                                            String integration_platforms,String lodgement_point,
                                            @NotNull String user) {
        this.lBase.log_off_from_salesforce();
        //this.lBase.login_into_salesforce_as(user);

        this.lBase.login_into_salesforce_as(UserConfig.getProperties().onBoardingUsername(),
                Utilities.decode(UserConfig.getProperties().onBoardingPassword()));

        this.lBase.salesforce.getLoginPage().getHeader()
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
                .getEParcelProductAttributesPage()
                .is_ready().enter_and_validate(product, product_industry, domestic_customer,
                        revenue_commitment, integration_platforms)
                .review_cart().add_lodgementPoint(product, lodgement_point)
                .getCartActions()
                .please("Save Cart & Bulk Edit Opportunity Products", "", "")
                .getEditProducts()
                .is_ready().back_to_opportunity(opp_name);

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions();
    }
}
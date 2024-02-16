package steps.regression_testing.existing_customer.eparcel;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Create_Unmerge_With_RKcode {

    private final Base lBase;

    public Create_Unmerge_With_RKcode(@NotNull Base base) {
        this.lBase = base;
    }

    //Create the contract by configuring the product and adding RK code
    @Given("{string} cart saved with {string} after validating {string}, {string}, " +
            "{string}, {string} and adding lodgement point {string} {string} by {string} user")
    public void save_cart_and_edit_products(String opp_name, String product, String product_industry,
                                            String domestic_customer, String revenue_commitment,
                                            String integration_platforms,String lodgement_point,
                                            String rk_code,
                                            @NotNull String user) {

        try {
            this.lBase.log_off_from_salesforce();

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
                    .enter_rk_code(rk_code)
                    .getCartActions()
                    .please("Save Cart & Bulk Edit Opportunity Products", "", "")
                    .getEditProducts()
                    .is_ready().back_to_opportunity(opp_name);

            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().is_ready()
                    .getOpportunities().getOpportunityHeader()
                    .is_ready(opp_name).verify_opportunity_actions();
        }
        catch(Exception e) {
            System.out.println("Execution failed because of following exception: "+e);
        }

    }
}

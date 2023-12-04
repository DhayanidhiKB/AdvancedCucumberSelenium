package steps.regression_testing.new_customer.eparcel.proposal;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.Constants;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import steps.Base;

import java.time.Duration;

public class CheckOut_EParcel_International_Product {
    private final Base lBase;

    public CheckOut_EParcel_International_Product(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("From {string} {string} user {string} {string} after validating its attributes" +
            " {string}, {string}, {string}, {string} and adding lodgement point {string}")
    public void checkOut_eParcel_international(String opp_name, @NotNull String user,
                                               String check_out_option, String product,
                                               String product_industry, String domestic_customer,
                                               String revenue_commitment, String integration_platforms,
                                               String lodgement_point) {
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
                .check_out()
                .getCheckOut()
                .is_ready().select(check_out_option).switch_to_default_content();

        switch (check_out_option) {
            case "Checkout Only":
                this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities()
                        .getOpportunityHeader()
                        .is_ready(opp_name)
                        .getOpportunitySubHeader()
                        .is_ready().scroll_into_view()
                        .getOpportunityTabSet()
                        .is_ready().products_contracts()
                        .getProductsAndContracts()
                        .is_ready();
                break;
            case "Generate Proposal Document":
                this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                        .getOpportunities().getOpportunityHeader().getAllProducts()
                        .getEParcelProductAttributesPage().getCartActions().getCheckOut()
                        .getEditProducts()
                        .is_ready().confirm_opportunity_products();
                Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(120));
                this.lBase.salesforce.getLoginPage().getHeader()
                        .getAppNavigator().getProposalPage()
                        .is_ready().verify_stage(Constants.PROPOSAL_STAGE);
                break;
        }
    }
}
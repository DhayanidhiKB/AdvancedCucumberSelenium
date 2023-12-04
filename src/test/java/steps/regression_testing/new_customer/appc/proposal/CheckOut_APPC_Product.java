package steps.regression_testing.new_customer.appc.proposal;

import com.my.salesforce.sandbox.Constants;
import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import pojos.Proposal;
import steps.Base;

public class CheckOut_APPC_Product {
    private final Base lBase;

    public CheckOut_APPC_Product(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("From {string} {string} user {string} {string} {string} psr after validating {string}, {string}, {string}, {string} and below details")
    public void proposal_and_checkout_appc_products(
            String opp_name, @NotNull String user, String check_out_option, String product,
            String psr_condition, String evaluated_spend, String tier, String transit_cover,
            String transit_cover_type, Proposal proposal) {
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
                .is_ready(opp_name).verify_opportunity_actions().add_pricing_product()
                .getAllProducts()
                .is_ready()
                .add_to_cart(product)
                .still_not_configured(product)
                .getAppcProductAttributesPage()
                .is_ready()
                .validate_default_attributes(proposal.getPrice_structure(),
                        proposal.getLodgement_zone(), proposal.getLodgement_zone1(),
                        proposal.getLodgement_zone2(), proposal.getCubic_status(),
                        proposal.getCubic_factor())
                .enter_evaluated_spend(evaluated_spend)
                .enter_transit_cover(transit_cover)
                .validate(tier, transit_cover_type)
                .review_cart()
                .getCheckOutAPPC()
                .is_ready()
                .check_out(psr_condition, this.lBase.compassQuoteID)
                .choose(user, check_out_option).switch_to_default_content();

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
                this.lBase.salesforce.getLoginPage().getHeader()
                        .getAppNavigator().getProposalPage()
                        .is_ready().verify_stage(Constants.PROPOSAL_STAGE);
                break;
        }
    }
}
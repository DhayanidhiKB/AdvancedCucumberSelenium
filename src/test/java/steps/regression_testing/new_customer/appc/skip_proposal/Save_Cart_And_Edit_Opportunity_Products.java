package steps.regression_testing.new_customer.appc.skip_proposal;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import pojos.Proposal;
import steps.Base;

public class Save_Cart_And_Edit_Opportunity_Products {
    private final Base lBase;

    public Save_Cart_And_Edit_Opportunity_Products(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("{string} cart saved with {string} after validating {string}, {string}, {string}, {string} and below details by {string} user")
    public void save_cart_and_edit_products(
            String opp_name, String product, String evaluated_spend, String tier, String transit_cover,
            String transit_cover_type, @NotNull String user, Proposal proposal) {
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
                .is_ready(opp_name)
                .verify_opportunity_actions()
                .add_pricing_product()
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
                .please("Save Cart & Bulk Edit Opportunity Products", "", "")
                .switch_to_default_content()
                .getEditProducts()
                .is_ready().back_to_opportunity(opp_name);

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions();
    }
}
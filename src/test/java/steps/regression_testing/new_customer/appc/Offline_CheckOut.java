package steps.regression_testing.new_customer.appc;

import com.my.salesforce.sandbox.Constants;
import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import pojos.Proposal;
import steps.Base;

public class Offline_CheckOut {
    private final Base lBase;

    public Offline_CheckOut(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("From {string} {string} user {string} {string} due to [{string}, {string}] after validating {string}, " +
            "{string}, {string}, {string} and below details")
    public void offline_checkout(String opp_name, @NotNull String user, String checkout_option,
                                 String product, String reason, String comment,
                                 String evaluated_spend, String tier, String transit_cover,
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
                .add_to_cart(product).configure(product)
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
                .is_ready().please(checkout_option, reason, comment);

        this.lBase.salesforce.getLoginPage().getHeader()
                .getAppNavigator().getProposalPage()
                .is_ready().verify_stage(Constants.PROPOSAL_STAGE);
    }
}
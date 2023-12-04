package steps.regression_testing.existing_customer.startrack.dov;

import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Initiates_DoV {
    private final Base lBase;

    public Initiates_DoV(@NotNull Base base) {
        this.lBase = base;
    }

    @When("User initiates dov[{string},{string}] by adding {string} after validating its attributes {string}, {string}, {string}, {string} and {string} from {string}")
    public void initiates_dov(String variation_category, String revenue_type, String product, String service_type,
                              String primary_suburb, String suburb, String tier, String checkout_option, @NotNull String opp_name) {
        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails()
                .getManageLodgementPointsAndAccountNumbersPage().getContractHeader()
                .getContractPage().getSteps()
                .initiate_dov()
                .getDovCategory()
                .is_ready().enter(variation_category, revenue_type)
                .back_to_shopping_cart();

        this.lBase.salesforce.getLoginPage().getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .add_pricing_product()
                .getAllProducts()
                .is_ready().add_to_cart(product).configure(product)
                .getStarTrackProductAttributesPage()
                .enter_and_validate(product, service_type, primary_suburb, tier, suburb)
                .review().refresh_pricing()
                .getCartActions()
                .toggle_and_check_out()
                .getCheckOut()
                .is_ready().select(checkout_option);

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities()
                .getOpportunityHeader()
                .is_ready(opp_name);
    }
}
package steps.regression_testing.existing_customer.startrack.dov.checkout.decrease;

import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import pojos.ContractConfirmationDetails;
import steps.Base;

public class DoV_Decrease_Generate_Agreement_Document {
    private final Base lBase;

    public DoV_Decrease_Generate_Agreement_Document(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} with below details after deleting {string} from {string}")
    public void checkout_process(String check_out_option, String product, String opp_name,
                                 @NotNull ContractConfirmationDetails details) {
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

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails()
                .is_ready().contract_entity_is(details.getEntity())
                .contract_type_is(details.getType())
                .included_product(details.getProduct());

        this.lBase.salesforce.getLoginPage().getHeader()
                .search(opp_name)
                .getAppNavigator().getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .is_ready().scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().products_contracts()
                .getProductsAndContracts()
                .is_ready().open_proposal(1);

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getProposalPage()
                .is_ready().verify_stage("Accepted without proposal document");
    }
}
package steps.regression_testing.existing_customer.startrack.dov.checkout.decrease;

import com.my.salesforce.sandbox.Constants;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class DoV_Decrease_Generate_Proposal_Document {
    private final Base lBase;

    public DoV_Decrease_Generate_Proposal_Document(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} {string} after deleting {string} from {string} and {string} " +
            "after presenting to {string}")
    public void dov_decrease_generate_proposal_document(String check_out_option, String document_name,
                                                        String product, String opp_name,
                                                        String sender, String later_status) {
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

        this.lBase.proposalName = this.lBase.salesforce.getLoginPage().getHeader()
                .getAppNavigator().getProposalPage()
                .is_ready().verify_status(Constants.INITIAL_PROPOSAL_STAGE, sender)
                .create_proposal(sender).verify_stage(Constants.PROPOSAL_STAGE)
                .present_the_proposal(document_name, sender).is_ready()
                .accept_the_proposal().is_ready()
                .verify_stage(later_status).getName();
    }
}
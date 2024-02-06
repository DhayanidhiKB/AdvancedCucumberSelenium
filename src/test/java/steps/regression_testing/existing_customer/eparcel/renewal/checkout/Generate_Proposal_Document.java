package steps.regression_testing.existing_customer.eparcel.renewal.checkout;

import com.my.salesforce.sandbox.Constants;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Generate_Proposal_Document {
    private final Base lBase;

    public Generate_Proposal_Document(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} {string} adding {string} with rk code[{string}] to the dov opportunity[{string}] by validating its " +
            "attributes {string}, {string}, {string}, {string}, lodgement point {string} " +
            "and {string} after presenting to {string}")
    public void generate_proposal_document(String check_out_option, String document_name, String product,
                                           String opp_name, String rk_code, String attribute_1,
                                           String attribute_2, String attribute_3, String attribute_4,
                                           String lodgement_point, String sender, String later_status) {
        this.lBase.salesforce.getLoginPage().getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .add_pricing_product()
                .getAllProducts()
                .is_ready().add_to_cart(product).configure(product)
                .getEParcelProductAttributesPage()
                .is_ready().enter_and_validate(product, attribute_1, attribute_2, attribute_3, attribute_4)
                .review_cart().add_lodgementPoint(product, lodgement_point)
                .back_to_shopping_cart(product).enter_rk_code(rk_code)
                .submit_for_approval(product)
                .getCartActions()
                .check_out()
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
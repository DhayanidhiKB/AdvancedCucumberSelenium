package steps.regression_testing.existing_customer.eparcel.dov.checkout.increase;

import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import pojos.ContractConfirmationDetails;
import steps.Base;

public class DoV_Increase_Generate_Agreement_Document {
    private final Base lBase;

    public DoV_Increase_Generate_Agreement_Document(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} with below details after adding {string} to the dov opportunity[{string}] by validating its " +
            "attributes {string}, {string}, {string}, {string} and lodgement point {string}")
    public void generate_agreement_document(String check_out_option, String product, String opp_name,
                                            String attribute_1, String attribute_2, String attribute_3,
                                            String attribute_4, String lodgement_point, @NotNull ContractConfirmationDetails details) {
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
                .back_to_shopping_cart(product)
                .getCartActions()
                .toggle_and_check_out()
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
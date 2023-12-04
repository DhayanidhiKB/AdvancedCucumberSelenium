package steps.regression_testing.new_customer.appc.skip_proposal;

import com.google.common.util.concurrent.Uninterruptibles;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import pojos.ContractConfirmationDetails;
import steps.Base;

import java.time.Duration;

public class Generate_Agreement_Document {
    private final Base lBase;

    public Generate_Agreement_Document(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} user performs credit assessment of the {string} using abn[{string}] for " +
            "{string} by entering {string}, {string}, {string}, {string} and {string} for" +
            " {string} redirected to contract page")
    public void generate_agreement_document(@NotNull String user, String opp_name, String abn,
                                            @NotNull String account_type, String industry,
                                            String email, String street_number, String sender,
                                            String check_out_option, @NotNull String product,
                                            @NotNull ContractConfirmationDetails details) {
        this.lBase.login_into_salesforce_as(user);

        this.lBase.salesforce.getLoginPage().getHeader()
                .search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().credit_assessment()
                .getCreditAssessmentPage()
                .is_it_ready().choose(account_type)
                .getSearchOrganisationPage()
                .is_it_ready().search(abn).select(abn)
                .credit_assess_using(sender, email, street_number, industry).save_assessment();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .is_ready().scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().credit_assessment()
                .getCreditAssessmentPage()
                .is_it_ready().verify_assessment_status();

        this.lBase.proposalName = this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .page_refresh().is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .is_ready().scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().products_contracts()
                .getProductsAndContracts()
                .is_ready().get_proposal_name();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .add_pricing_product()
                .getAllProducts()
                .is_ready().review();

        if (product.contains("Australia Post Parcel Contract")) {
            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                    .getOpportunities().getOpportunityHeader().getAllProducts()
                    .getAppcProductAttributesPage().getCheckOutAPPC()
                    .is_ready().check_out()
                    .choose(user, check_out_option).switch_to_default_content()
                    .getEditProducts()
                    .is_ready().confirm_opportunity_products();
        } else if (product.contains("Premium")) {
            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                    .getOpportunities().getOpportunityHeader().getAllProducts()
                    .getStarTrackProductAttributesPage().getCartActions()
                    .check_out()
                    .getCheckOut()
                    .is_ready().no_of_options(user).select(check_out_option)
                    .switch_to_default_content()
                    .getEditProducts()
                    .is_ready().confirm_opportunity_products();
        } else {
            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                    .getOpportunities().getOpportunityHeader().getAllProducts()
                    .getEParcelProductAttributesPage().getCartActions()
                    .check_out()
                    .getCheckOut()
                    .is_ready().no_of_options(user).select(check_out_option)
                    .switch_to_default_content()
                    .getEditProducts()
                    .is_ready().confirm_opportunity_products();
        }
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(120));
        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails()
                .is_ready().contract_entity_is(details.getEntity()).key_contact_is(sender)
                .contract_type_is(details.getType())
                .included_product(details.getProduct());
    }
}
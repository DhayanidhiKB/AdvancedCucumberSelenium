package steps.regression_testing.new_customer.appc.skip_proposal;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import pojos.SubAccountRequest;
import steps.Base;

public class Generate_Agreement_Document_With_SubAccount {

    private final Base lBase;

    public Generate_Agreement_Document_With_SubAccount(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} user performs credit assessment of the {string} using abn[{string}] " +
            "for {string} by entering {string}, {string}, {string}, {string} and creates " +
            "{string} along with sub account [{string}, {string}] with below details")
    public void generate_agreement(String user, String opp_name, String abn, @NotNull String account_type,
                                   String industry, String email, String street_number, String sender,
                                   String check_out_option, String sub_account_name, String sub_account_name2,
                                   @NotNull SubAccountRequest subAccountRequest) {
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
                .is_ready().search(opp_name)
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

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities()
                .getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                .getCreditAssessmentPage().getSearchOrganisationPage()
                .getNewSubAccountRequestPage()
                .is_ready()
                .getParentDetails()
                .getRequestDetails()
                .is_ready().enter(sub_account_name + "_" + Utilities.epochSeconds(),
                        sub_account_name2 + "_" + Utilities.epochSeconds())
                .set_and_verify(subAccountRequest.getParcel_send(), sender)
                .scroll_to_sub_account_name()
                .select(subAccountRequest.getELMS())
                .getAddressOfBusiness()
                .is_ready().enter(subAccountRequest.getAddress_line_1(), subAccountRequest.getAddress_line_2(),
                        subAccountRequest.getSub_urb(), subAccountRequest.getState(), subAccountRequest.getPost_code())
                .getOutletDetails()
                .is_ready().scroll_to_bottom().search_for(subAccountRequest.getLodgement_point())
                .save_and_finalize();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities()
                .getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().credit_assessment()
                .getCreditAssessmentPage()
                .is_it_ready().verify_assessment_status();

        this.lBase.proposalName = this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .page_refresh().is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().products_contracts()
                .getProductsAndContracts()
                .is_ready().get_proposal_name();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .add_pricing_product()
                .getAllProducts()
                .is_ready().review()
                .getAppcProductAttributesPage().getCheckOutAPPC()
                .is_ready().check_out()
                .choose(user, check_out_option);
    }
}
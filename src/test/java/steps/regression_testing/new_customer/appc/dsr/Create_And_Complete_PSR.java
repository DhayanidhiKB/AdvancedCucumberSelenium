package steps.regression_testing.new_customer.appc.dsr;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import pojos.PSR;
import steps.Base;

public class Create_And_Complete_PSR {
    private final Base lBase;

    public Create_And_Complete_PSR(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("As Sales user submitted {string} from {string} with transit cover[{string}, {string}], revenue[{string}], volume[{string}] which is then {string} by pricing user entering {string}, {string}, {string} and {string}")
    public void create_and_complete_psr(
            String record_type, String opp_name, String transit_cover_type, String transit_cover_amount,
            String annual_revenue, String annual_volume, String status, String quote_id, String zone, String tier, String evaluated_spend, @NotNull PSR psr) {
        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();

        this.lBase.salesforce.visit(UserConfig.getProperties().appUrl())
                .getLoginPage()
                .is_ready().login_as(UserConfig.getProperties().salesUsername(),
                        Utilities.decode(UserConfig.getProperties().salesPassword()))
                .getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator().is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).new_deal_support_request()
                .getNewDealSupportRequestPage()
                .is_ready().choose_request_type(record_type)
                .getPricingSupportRequestPage()
                .is_ready().save_psr(psr.getAgreement_duration(),
                        psr.getAgreement_type(), transit_cover_type, transit_cover_amount,
                        annual_revenue, annual_volume, psr.getCatalyst(), psr.getSales_justification());

        this.lBase.psrName = this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities().getOpportunityHeader()
                .getNewDealSupportRequestPage()
                .getDsrHeader()
                .is_ready().not_submitted().submit_psr()
                .getDealSupportRequestDetailsPage()
                .verify_psr(psr.getOwner()).get_psr_name();

        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();

        this.lBase.salesforce.visit(UserConfig.getProperties().appUrl())
                .getLoginPage()
                .is_ready().login_as(UserConfig.getProperties().pricingUsername(),
                        Utilities.decode(UserConfig.getProperties().pricingPassword()))
                .getHeader()
                .is_ready().search(this.lBase.psrName)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader().getNewDealSupportRequestPage()
                .getDsrHeader()
                .is_ready()
                .getDealSupportRequestDetailsPage()
                .edit_pricing_recommendations(quote_id, psr.getRecommendation(),
                        zone, tier, psr.getEnd_date(), psr.getApproved_event(), evaluated_spend);

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader().
                getNewDealSupportRequestPage().getDsrHeader().complete_deal_support_request(status);

        this.lBase.psrApprovedEvent = psr.getApproved_event();
        this.lBase.compassQuoteID = quote_id;
    }
}
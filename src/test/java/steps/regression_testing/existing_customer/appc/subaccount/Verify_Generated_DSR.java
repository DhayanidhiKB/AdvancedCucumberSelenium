package steps.regression_testing.existing_customer.appc.subaccount;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Then;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Verify_Generated_DSR {
    private final Base lBase;

    public Verify_Generated_DSR(@NotNull Base base) {
        this.lBase = base;
    }

    @Then("Verify whether {int} {string} DSR with status as {string} is generated for {string} product under customer [{string}, {string}]")
    public void verify_generated_dsr(int count, String dsr_queue, String dsr_status, String product, String org_name, String abn) {
        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();

        this.lBase.salesforce.visit(UserConfig.getProperties().appUrl())
                .getLoginPage()
                .is_ready().login_as(UserConfig.getProperties().salesUsername(),
                        Utilities.decode(UserConfig.getProperties().salesPassword()))
                .getHeader().is_ready()
                .getAppNavigator()
                .is_ready().set("AP Sales").is_ready().organisations()
                .getOrganisations()
                .is_ready().search_for(org_name)
                .getOrganisationDetails()
                .is_ready(org_name, abn).support_requests();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .getOpportunitySubHeader().getOpportunityTabSet()
                .getSupportRequestsPage()
                .is_ready().noOf_support_requests(lBase.noOfDSR + count);

        if (count > 0) {
            System.out.println("index:" + lBase.noOfDSR);
            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                    .getOpportunities().getOpportunityHeader()
                    .getOpportunitySubHeader().getOpportunityTabSet()
                    .getSupportRequestsPage()
                    .open_dsr(lBase.noOfDSR);

            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                    .getOpportunities().getOpportunityHeader()
                    .getNewDealSupportRequestPage()
                    .getDsrHeader()
                    .is_ready()
                    .getDealSupportRequestDetailsPage()
                    .verify(dsr_queue, dsr_status)
                    .verify_product(product);
        }
        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();
    }
}
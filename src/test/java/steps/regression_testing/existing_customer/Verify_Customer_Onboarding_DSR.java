package steps.regression_testing.existing_customer;

import io.cucumber.java.en.Then;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Verify_Customer_Onboarding_DSR {
    private final Base lBase;

    public Verify_Customer_Onboarding_DSR(@NotNull Base base) {
        this.lBase = base;
    }

    @Then("Verify whether {int} {string} dsr can be seen under {string} in {string} status")
    public void verify_dsr(int no_of_dsr, String queue, String opp_name, String status) {
        this.lBase.salesforce.getLoginPage().getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name)
                .getOpportunitySubHeader()
                .is_ready().scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().support_requests()
                .getSupportRequestsPage()
                .is_ready().noOf_support_requests(no_of_dsr);

        switch (no_of_dsr) {
            case 1:
                this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                        .getOpportunities().getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                        .getSupportRequestsPage()
                        .open_dsr(0);
                break;
            case 2:
                this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                        .getOpportunities().getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                        .getSupportRequestsPage()
                        .is_ready().open_dsr(1);
                break;
            case 3:
                this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                        .getOpportunities().getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                        .getSupportRequestsPage()
                        .is_ready().open_dsr(2);
                break;
        }

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .getNewDealSupportRequestPage()
                .getDsrHeader()
                .is_ready()
                .getDealSupportRequestDetailsPage()
                .verify(queue, status);
    }
}
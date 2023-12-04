package steps.regression_testing.new_customer.appc.dsr;

import io.cucumber.java.en.*;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Validating_DSR_and_PSR {
    private final Base lBase;

    public Validating_DSR_and_PSR(@NotNull Base base) {
        this.lBase = base;
    }

    @Then("Make sure that there are {int} {string} dsr under {string} in {string} " +
            "status {string} psr")
    public void validating_dsr_and_psr(int no_of_dsr, String queue, String opp_name,
                                       String status, @NotNull String condition) {
        this.lBase.salesforce.getLoginPage().getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator().is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().support_requests()
                .getSupportRequestsPage()
                .is_ready();

        switch (condition) {
            case "without":
                switch (no_of_dsr) {
                    case 1:
                        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                                .getOpportunities().getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                                .getSupportRequestsPage()
                                .is_ready().noOf_support_requests(1);
                        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                                .getOpportunities().getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                                .getSupportRequestsPage()
                                .is_ready().open_dsr(0);
                        break;
                    case 2:
                        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                                .getOpportunities().getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                                .getSupportRequestsPage()
                                .is_ready().noOf_support_requests(2);
                        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                                .getOpportunities().getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                                .getSupportRequestsPage()
                                .is_ready().open_dsr(1);
                        break;
                }
                break;
            case "with":
                switch (no_of_dsr) {
                    case 1:
                        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                                .getOpportunities().getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                                .getSupportRequestsPage()
                                .is_ready().noOf_support_requests(2);
                        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                                .getOpportunities().getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                                .getSupportRequestsPage()
                                .open_dsr(1);
                        break;
                    case 2:
                        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                                .getOpportunities().getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                                .getSupportRequestsPage()
                                .is_ready().noOf_support_requests(3);
                        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                                .getOpportunities().getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                                .getSupportRequestsPage()
                                .open_dsr(2);
                        break;
                }
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
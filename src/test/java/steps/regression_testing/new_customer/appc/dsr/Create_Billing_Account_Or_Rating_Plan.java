package steps.regression_testing.new_customer.appc.dsr;

import io.cucumber.java.en.Then;
import org.jetbrains.annotations.NotNull;
import pojos.SAPIntegrationStatus;
import steps.Base;

public class Create_Billing_Account_Or_Rating_Plan {
    private final Base lBase;

    public Create_Billing_Account_Or_Rating_Plan(@NotNull Base base) {
        this.lBase = base;
    }

    @Then("Validate the response when {string} request is {string} for {string} to SAP " +
            "and finally {string} is {string} with details[{string}, {string}, {string}]")
    public void create_billing_account_or_rating_plan(String action, String dsr_status, String product,
                                                      String opp_name, String opp_stage,
                                                      String next_step, String reason, String comment,
                                                      @NotNull SAPIntegrationStatus details) {
        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .getNewDealSupportRequestPage()
                .getDsrHeader()
                .create_billing_account_rating_plan(action);

        switch (action) {
            case "Create Billing Account/Rating Plan":
                this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                        .getOpportunities().getOpportunityHeader()
                        .getNewDealSupportRequestPage()
                        .getDsrHeader().getDealSupportRequestDetailsPage()
                        .getSapBillingAccountRequestPage()
                        .is_ready().submit_request();
                break;
            case "Create Rating Plan":
                this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                        .getOpportunities().getOpportunityHeader()
                        .getNewDealSupportRequestPage()
                        .getDsrHeader().getDealSupportRequestDetailsPage()
                        .getSapRatingPlanRequestPage()
                        .is_ready().submit_request();
                break;
        }

        this.lBase.transactionID = this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .getNewDealSupportRequestPage().getDsrHeader()
                .is_ready().dsr_is(dsr_status)
                .getDealSupportRequestDetailsPage().getIntegrationDetails()
                .verify_integration_status(details.getStatus()).get_transaction_id();
        System.out.println("Transaction ID:" + this.lBase.transactionID);

        this.lBase.salesforce.getLoginPage().getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .close_opportunity(opp_stage, next_step, reason, comment)
                .opportunity_stage_is(opp_stage);

        this.lBase.log_off_from_salesforce();
    }
}
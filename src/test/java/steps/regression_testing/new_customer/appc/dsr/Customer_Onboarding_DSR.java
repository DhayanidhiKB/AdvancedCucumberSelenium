package steps.regression_testing.new_customer.appc.dsr;

import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import pojos.DSR;
import steps.Base;

public class Customer_Onboarding_DSR {
    private final Base lBase;

    public Customer_Onboarding_DSR(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("{string} a {string} DSR for {string} to create {string}")
    public void customer_onboarding_dsr(String status, String record_type, String product,
                                        String work_type, @NotNull DSR dsr) {
        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .new_deal_support_request()
                .getNewDealSupportRequestPage()
                .is_ready()
                .choose_request_type(record_type)
                .fill(product, dsr.getDescription(), work_type, dsr.getSupport_work_type())
                .getDsrHeader()
                .is_ready()
                .not_submitted()
                .submit_dsr()
                .getDealSupportRequestDetailsPage()
                .verify(record_type, status);
    }
}
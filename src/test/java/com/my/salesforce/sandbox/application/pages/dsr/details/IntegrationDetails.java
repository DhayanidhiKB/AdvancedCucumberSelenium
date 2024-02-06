package com.my.salesforce.sandbox.application.pages.dsr.details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.details.DealSupportRequestDetailsPageElements;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IntegrationDetails extends BasePage implements DealSupportRequestDetailsPageElements {

    public IntegrationDetails verify_integration_status(String integration_status) {
        page_refresh_and_wait();
        page_refresh_and_wait();
        page_refresh_and_wait();
        locate(Edit, "Billing Account Number").scrollIntoView(true);
        isItVisible(locate(Verify, "Integration Status", integration_status));
        isItVisible(locate(Verify, "Integration Status Description", integration_status));
        return this;
    }

    public IntegrationDetails verify_rating_integration(String status, String description) {
        isItVisible(locate(Verify, "Rating Integration Status", status));
        isItVisible(locate(Verify, "Rating Integration Status Description", description));
        return this;
    }

    public String get_transaction_id() {
        String id = locate(GetText, "Integration Transaction Id").getText();
        assertThat(id).isNotEmpty();
        return id;
    }

    public IntegrationDetails(WebDriver driver) {
        super(driver);
    }
}
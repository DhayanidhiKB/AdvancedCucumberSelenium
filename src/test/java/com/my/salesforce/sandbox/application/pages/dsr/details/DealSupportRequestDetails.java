package com.my.salesforce.sandbox.application.pages.dsr.details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.details.DealSupportRequestDetailsPageElements;
import com.my.salesforce.sandbox.application.pages.dsr.SAPBillingAccountRequest;
import com.my.salesforce.sandbox.application.pages.dsr.SAPRatingPlanRequest;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class DealSupportRequestDetails extends BasePage implements DealSupportRequestDetailsPageElements {
    public DealSupportRequestDetails verify(String owner, String status) {
        isItVisible(locate(Owner, owner));
        isItVisible(locate(Status, status));
        return this;
    }

    public DealSupportRequestDetails verify_psr(String queue) {
        isItInteractable(locate(Edit, "Name of Product Specialist"));
        isItVisible(locate(Owner, queue));
        return this;
    }

    public void verify_product(String product) {
        shouldHaveText(Product, product);
    }

    public String get_psr_name() {
        return locate(Name, "1").getText();
    }

    public String verify_integration_details(String integration_status,
                                             String rating_integration_status,
                                             String rating_integration_status_description) {
        return getIntegrationDetails().verify_integration_status(integration_status)
                .verify_rating_integration(rating_integration_status,
                        rating_integration_status_description).get_transaction_id();
    }

    public void edit_pricing_recommendations(String quoteID, String recommendation,
                                             String pricingStructure, String tier,
                                             String endDate, String approvedEvent,
                                             String evaluated_spend) {
        getPricingRecommendations().edit(quoteID, recommendation, pricingStructure, tier,
                endDate, approvedEvent, evaluated_spend);
    }

    private PricingRecommendations pricingRecommendations;
    private IntegrationDetails integrationDetails;
    private SAPBillingAccountRequest sapBillingAccountRequestPage;
    private SAPRatingPlanRequest sapRatingPlanRequestPage;

    public DealSupportRequestDetails(WebDriver driver) {
        super(driver);
        setPricingRecommendations(new PricingRecommendations(lDriver));
        setIntegrationDetails(new IntegrationDetails(lDriver));
        setSapBillingAccountRequestPage(new SAPBillingAccountRequest(lDriver));
        setSapRatingPlanRequestPage(new SAPRatingPlanRequest(lDriver));
    }
}
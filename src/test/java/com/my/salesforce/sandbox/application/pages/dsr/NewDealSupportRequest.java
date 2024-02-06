package com.my.salesforce.sandbox.application.pages.dsr;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.NewDealSupportRequestPageElements;
import com.my.salesforce.sandbox.application.pages.dsr.customer_onboarding.CustomerOnboardingDSR;
import com.my.salesforce.sandbox.application.pages.dsr.details.DSRHeader;
import com.my.salesforce.sandbox.application.pages.dsr.psr.PricingSupportRequestPage;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

@Getter
@Setter
public class NewDealSupportRequest extends BasePage implements NewDealSupportRequestPageElements {
    public NewDealSupportRequest is_ready() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        isItVisible(Header);
        isItVisible(SubHeader);
        isItVisible(RequestTypeBody);
        return this;
    }

    public NewDealSupportRequest choose_request_type(String type) {
        isItInteractable(locate(RequestType, type)).scrollIntoView(true).click();
        isItInteractable(Next);
        mouseOverAndClickOn(Next);
        return this;
    }

    public NewDealSupportRequest fill(String product, String description, String work_type, String support_work_type) {
        getCustomerOnboardingDSRPage().is_ready().save().validate_error()
                .enter(product, description, work_type, support_work_type).save();
        return this;
    }

    private CustomerOnboardingDSR customerOnboardingDSRPage;
    private PricingSupportRequestPage pricingSupportRequestPage;
    private DSRHeader dsrHeader;

    public NewDealSupportRequest(WebDriver driver) {
        super(driver);
        setCustomerOnboardingDSRPage(new CustomerOnboardingDSR(lDriver));
        setPricingSupportRequestPage(new PricingSupportRequestPage(lDriver));
        setDsrHeader(new DSRHeader(lDriver));
    }
}
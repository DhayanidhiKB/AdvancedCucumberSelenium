package com.my.salesforce.sandbox.application.pages.dsr;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.SAPBillingAccountRequestPageElements;
import org.openqa.selenium.WebDriver;

public class SAPBillingAccountRequest extends BasePage implements SAPBillingAccountRequestPageElements {
    public SAPBillingAccountRequest is_ready() {
        isItVisible(Message);
        isItInteractable(locate(Button, "Submit Request"));
        isItInteractable(locate(Button, "Cancel"));
        return this;
    }

    public void submit_request() {
        locate(Button, "Submit Request").scrollIntoView(true);
        mouseOverAndClickOn(locate(Button, "Submit Request"));
        isItInteractable(Close);
        mouseOverAndClickOn(Close);
    }

    public SAPBillingAccountRequest(WebDriver driver) {
        super(driver);
    }
}
package com.my.salesforce.sandbox.application.pages.opportunities.creditassessment;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.creditassessment.FinalizeSubAccountRequestElements;
import org.openqa.selenium.WebDriver;

public class FinalizeSubAccountRequest extends BasePage implements FinalizeSubAccountRequestElements {
    public FinalizeSubAccountRequest is_ready() {
        isItVisible(Header);
        isItVisible(Message1);
        isItInteractable(Cancel);
        isItInteractable(FinalizeRequest);
        return this;
    }

    public void finalize_request() {
        clickOn(FinalizeRequest);
        isItVisible(Message2);
        clickOn(Close);
    }

    public FinalizeSubAccountRequest(WebDriver driver) {
        super(driver);
    }
}
package com.my.salesforce.sandbox.application.pages.dsr;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.SAPRatingPlanRequestPageElements;
import org.openqa.selenium.WebDriver;

public class SAPRatingPlanRequest extends BasePage implements SAPRatingPlanRequestPageElements {
    public SAPRatingPlanRequest is_ready() {
        isItVisible(Header);
        isItVisible(SubmitMessage);
        isItInteractable(locate(Button, "Confirm"));
        isItInteractable(locate(Button, "Close"));
        return this;
    }

    public void submit_request() {
        clickOn(locate(Button, "Confirm"));
        isItInteractable(locate(Button, "Close"));
        isItVisible(SuccessMessage);
        clickOn(Close);
    }

    public SAPRatingPlanRequest(WebDriver driver) {
        super(driver);
    }
}
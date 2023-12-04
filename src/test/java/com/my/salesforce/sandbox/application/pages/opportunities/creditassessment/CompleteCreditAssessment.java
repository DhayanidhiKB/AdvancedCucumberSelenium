package com.my.salesforce.sandbox.application.pages.opportunities.creditassessment;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.creditassessment.CreditAssessmentMessages;
import org.openqa.selenium.WebDriver;

public class CompleteCreditAssessment extends BasePage implements CreditAssessmentMessages {
    public CompleteCreditAssessment is_displayed() {
        isItVisible(CompleteAndApprove);
        isItInteractable(OK);
        return this;
    }

    public void accepted() {clickOn(OK);}

    public CompleteCreditAssessment(WebDriver driver) {
        super(driver);
    }
}
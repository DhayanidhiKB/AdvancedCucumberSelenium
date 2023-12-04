package com.my.salesforce.sandbox.application.pages.opportunities.creditassessment;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.creditassessment.MoveOrCreateNewCreditAssessmentElement;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

public class MoveOrCreateNewCreditAssessment extends BasePage implements MoveOrCreateNewCreditAssessmentElement {
    public MoveOrCreateNewCreditAssessment is_ready() {
        isItVisible(Header);
        isItVisible(Table);
        isItInteractable(CancelSelection);
        return this;
    }

    public MoveOrCreateNewCreditAssessment action(@NotNull String action) {
        switch (action) {
            case "Create New Credit Assessment":
                clickOn(locate(Option, action));
                isItVisible(NewCreditAssessmentMessage);
                break;
            case "Move Credit Assessment":
                clickOn(RadioOption);
                clickOn(locate(Option, action));
                isItVisible(MoveMessage1);
                isItVisible(MoveMessage2);
                break;
        }
        isItInteractable(Cancel);
        isItInteractable(Ok);
        clickOn(Cancel);
        return this;
    }

    public MoveOrCreateNewCreditAssessment(WebDriver driver) {
        super(driver);
    }
}
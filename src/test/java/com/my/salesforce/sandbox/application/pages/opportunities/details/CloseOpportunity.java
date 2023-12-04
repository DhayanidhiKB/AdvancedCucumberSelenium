package com.my.salesforce.sandbox.application.pages.opportunities.details;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.details.CloseOpportunityElements;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CloseOpportunity extends BasePage implements CloseOpportunityElements {
    public CloseOpportunity is_ready() {
        isItInteractable(Header);
        isItInteractable(CancelClosure);
        isItInteractable(ConfirmOPC);
        return this;
    }

    public CloseOpportunity confirm_opc() {
        $(ConfirmOPC).scrollIntoView(true).shouldBe(interactable).click();
        return this;
    }

    public void close_opportunity(String stage_name, String next_steps,
                                  String reason, String comment) {
        clickOn(StageName);
        clickOn(locate(Option, stage_name));
        $(ClosedComments).scrollIntoView(true);
        clickOn(NextSteps);
        clickOn(locate(Option, next_steps));
        clickOn(ClosedReason);
        clickOn(locate(Option, reason));
        setValue(comment, ClosedComments);
        clickOn(CloseOpportunity);
    }

    public CloseOpportunity data_integrity_compliance() {
        isItVisible(DataIntegrity);
        clickOn(Agree);
        clickOn(Next);
        return this;
    }

    public void confirm_dsr() {
        isItVisible(OpenDSRs);
        clickOn(Next);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(7));
    }

    public void close() {
        isItVisible(OpportunityClosed);
        clickOn(Close);
    }

    public CloseOpportunity(WebDriver driver) {
        super(driver);
    }
}
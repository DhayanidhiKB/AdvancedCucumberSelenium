package com.my.salesforce.sandbox.application.pages.opportunities.details.headers;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.details.sub_header.OutStandingActionsElements;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OutStandingActions extends BasePage implements OutStandingActionsElements {
    public OutStandingActions is_ready() {
        isItVisible(ThemeError);
        return this;
    }

    public void can_progress() {
        $(ThemeSuccess).shouldBe(visible).shouldHave(appear);
    }

    public void verify_actions_for_the_stage(@NotNull String stage) {
        switch (stage) {
          case "Identify":
                isItVisible(MessageQualifyToPropose1);
                break;
            case "Qualify":
                isItVisible(MessageQualifyToPropose1);
                isItVisible(MessageQualifyToPropose2);
                isItVisible(MessageQualifyToPropose3);
                break;
            case "Propose":
                isItVisible(MessageProposeToNegotiate);
                break;
            case "Negotiate":
                isItVisible(MessageNegotiateToClosedWon1);
                isItVisible(MessageNegotiateToClosedWon2);
                break;
        }
    }

    public OutStandingActions(WebDriver driver) {
        super(driver);
    }
}
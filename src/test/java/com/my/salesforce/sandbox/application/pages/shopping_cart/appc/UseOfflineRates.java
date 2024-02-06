package com.my.salesforce.sandbox.application.pages.shopping_cart.appc;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.appc.UseOfflineRatesElements;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class UseOfflineRates extends BasePage implements UseOfflineRatesElements {
    public UseOfflineRates is_ready() {
        isItVisible(Header);
        isItInteractable(Reason);
        isItInteractable(Comments);
        isItInteractable(Next);
        isItInteractable(Cancel);
        return this;
    }

    public UseOfflineRates fill(String reason, String comment) {
        actions().click($(Reason)).pause(Duration.ofSeconds(1)).sendKeys(reason)
                .pause(Duration.ofSeconds(1)).sendKeys(Keys.ENTER).build().perform();
        setValue(comment, Comments);
        return this;
    }

    public void next() {
        $(Next).click();
    }

    public UseOfflineRates(WebDriver driver) {
        super(driver);
    }
}
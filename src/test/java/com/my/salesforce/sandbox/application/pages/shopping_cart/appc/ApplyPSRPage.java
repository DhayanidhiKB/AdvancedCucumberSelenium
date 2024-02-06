package com.my.salesforce.sandbox.application.pages.shopping_cart.appc;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.appc.ApplyPSRPageElements;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ApplyPSRPage extends BasePage implements ApplyPSRPageElements {
    public ApplyPSRPage is_it_ready() {
        switch_to_frame(iFrame);
        isItVisible(FirstHeader);
        isItVisible(SecondHeader);
        isItVisible(BackToCart);
        isItVisible(ApplyPSR);
        return this;
    }

    public ApplyPSRPage verify(String quote_id) {
        shouldHaveText(QuoteID, quote_id);
        return this;
    }

    public ApplyPSRPage apply_psr() {
        clickOn(ApplyPSR);
        isItVisible(SuccessMessage);
        return this;
    }

    public void back_to_cart() {
        $(BackToCart).shouldBe(visible).shouldBe(enabled).shouldHave(appear).click();
    }

    public ApplyPSRPage(WebDriver driver) {
        super(driver);
    }
}
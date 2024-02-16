package com.my.salesforce.sandbox.application.pages.contract.dov;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.dov.NewDoVLanguageElements;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class NewDoVLanguage extends BasePage implements NewDoVLanguageElements {
    public NewDoVLanguage is_ready() {
        isItVisible(Header);
        isItInteractable(locate(Dov, "DoV Type"));
        isItInteractable(locate(Dov, "DOV Reason"));
        isItInteractable(SaveAndEdit);
        isItInteractable(Cancel);
        return this;
    }

    // parameters Open Ended (or) Add new products
    public void save(String dov_type, String dov_reason, String product) {
        clickOn(locate(Dov, "DoV Type"));
        clickOn(locate(Option, dov_type));
        locate(Dov, "DOV Reason").scrollIntoView(true).click();
        clickOn(locate(Option, dov_reason));
        locate(Dov, "DoV Product").scrollIntoView(true);
        isItInteractable(locate(Dov, "DoV Product"));
        actions().click(locate(Dov, "DoV Product")).pause(Duration.ofSeconds(1))
                .sendKeys(product).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ENTER).build().perform();
        clickOn(Save);
    }

    public NewDoVLanguage(WebDriver driver) {
        super(driver);
    }
}
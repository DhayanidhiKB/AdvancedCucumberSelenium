package com.my.salesforce.sandbox.application.pages.opportunities.details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.details.ActivityElements;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class Activity extends BasePage implements ActivityElements {
    public Activity is_ready() {
        isItInteractable(NewTask);
        return this;
    }

    public Activity new_task(String subject, String contact) {
        clickOn(NewTask);
        isItInteractable(Subject);
        isItInteractable(SearchContacts);
        actions().click(SearchContacts).sendKeys(contact).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ARROW_DOWN).pause(Duration.ofSeconds(1)).sendKeys(Keys.ENTER).build().perform();
        clickOn(Subject);
        clickOn(locate(Option, subject));
        return this;
    }

    public Activity save() {
        clickOn(Save);
        return this;
    }

    public Activity(WebDriver driver) {
        super(driver);
    }
}
package com.my.salesforce.sandbox.application.pages.organisations.contacts;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.contacts.NewContactElements;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class NewContact extends BasePage implements NewContactElements {
    public NewContact is_ready() {
        isItVisible(Heading);
        return this;
    }

    public NewContact fill(String title, String first_name, String last_name, String email) {
        clickOn(Title);
        clickOn(locate(Option, title));
        setValue(first_name, locate(Enter, "First Name"));
        setValue(last_name, locate(Enter, "Last Name"));
        setValue(email, locate(Enter, "Email"));
        return this;
    }

    public NewContact save() {
        clickOn(Save);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        if (WarningButton.size() > 0) {
            clickOn(Save);
        }
        return this;
    }

    public NewContact validate_error() {
        isItInteractable(NameError);
        isItInteractable(ErrorIcon);
        return this;
    }

    public NewContact(WebDriver driver) {
        super(driver);
    }
}
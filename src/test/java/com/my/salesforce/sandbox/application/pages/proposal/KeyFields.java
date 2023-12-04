package com.my.salesforce.sandbox.application.pages.proposal;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.proposal.KeyFieldsElements;
import org.openqa.selenium.WebDriver;

public class KeyFields extends BasePage implements KeyFieldsElements {
    public KeyFields is_ready(String contact) {
        isItInteractable(locate(KeyContact, contact));
        return this;
    }

    public KeyFields(WebDriver driver) {
        super(driver);
    }
}
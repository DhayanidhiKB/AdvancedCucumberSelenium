package com.my.salesforce.sandbox.application.pages.opportunities.new_opportunity;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity.KeyContactElements;
import org.openqa.selenium.WebDriver;

public class KeyContact extends BasePage implements KeyContactElements {
    public KeyContact is_ready() {
        isItVisible(Header);
        return this;
    }

    public KeyContact select(String contact_name) {
        clickOn(locate(Link, contact_name));
        return this;
    }

    public KeyContact(WebDriver driver) {
        super(driver);
    }
}
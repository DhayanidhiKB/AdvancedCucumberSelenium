package com.my.salesforce.sandbox.application.pages.opportunities.related.csq;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.related.csq.NewCSQPageElements;
import org.openqa.selenium.WebDriver;

public class NewCSQ extends BasePage implements NewCSQPageElements {
    public NewCSQ is_it_ready() {
        isItVisible(Header);
        isItInteractable(locate(TextArea, "Customer brief"));
        isItInteractable(locate(Button, "Save"));
        return this;
    }

    public void save_csq(String customer_brief) {
        setValue(customer_brief, locate(TextArea, "Customer brief"));
        clickOn(locate(Button, "Save"));
    }

    public NewCSQ(WebDriver driver) {
        super(driver);
    }
}
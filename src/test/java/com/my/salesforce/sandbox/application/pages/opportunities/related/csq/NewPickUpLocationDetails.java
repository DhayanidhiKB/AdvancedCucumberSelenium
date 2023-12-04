package com.my.salesforce.sandbox.application.pages.opportunities.related.csq;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.related.csq.NewPickUpLocationDetailsElements;
import org.openqa.selenium.WebDriver;

public class NewPickUpLocationDetails extends BasePage implements NewPickUpLocationDetailsElements {
    public NewPickUpLocationDetails is_it_ready() {
        isItVisible(Header);
        isItInteractable(locate(Action, "New Task"));
        isItInteractable(locate(Action, "Delete"));
        return this;
    }

    public NewPickUpLocationDetails(WebDriver driver) {
        super(driver);
    }
}
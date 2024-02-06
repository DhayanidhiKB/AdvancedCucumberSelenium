package com.my.salesforce.sandbox.application.pages.opportunities.related.csq.new_freight;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.related.csq.new_freight.PremiumElements;
import org.openqa.selenium.WebDriver;

public class PickupDetails extends BasePage implements PremiumElements {
    public void pickup_details(String scheduling, String day) {
        clickOn(locate(ListBox, "Pick-up Scheduling"));
        clickOn(locate(Option, scheduling));
        clickOn(locate(DualListBox, "Permanent pick-up days", day));
        clickOn(locate(Arrow, "Permanent pick-up days", "Chosen"));
    }

    public PickupDetails(WebDriver driver) {
        super(driver);
    }
}
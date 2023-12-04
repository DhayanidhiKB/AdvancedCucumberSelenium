package com.my.salesforce.sandbox.application.pages.opportunities.new_opportunity;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity.StarTrackPrimaryPickupElements;
import org.openqa.selenium.WebDriver;

public class StarTrackPrimaryPickup extends BasePage implements StarTrackPrimaryPickupElements {
    public StarTrackPrimaryPickup is_it_ready() {
        isItVisible(Header);
        isItInteractable(Search);
        return this;
    }

    public void select(String postcode) {
        clickOn(locate(Link, postcode));
    }

    public StarTrackPrimaryPickup(WebDriver driver) {
        super(driver);
    }
}
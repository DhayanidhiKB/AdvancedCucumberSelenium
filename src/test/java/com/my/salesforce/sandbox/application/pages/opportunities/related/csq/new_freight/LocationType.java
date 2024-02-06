package com.my.salesforce.sandbox.application.pages.opportunities.related.csq.new_freight;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.related.csq.new_freight.PremiumElements;
import org.openqa.selenium.WebDriver;

public class LocationType extends BasePage implements PremiumElements {
    public void location_type(String location_type) {
        clickOn(locate(DualListBox, "Location Type", "Retail"));
        locate(DualListBox, "Location Type", "Offices").scrollIntoView(true);
        clickOn(locate(DualListBox, "Location Type", location_type));
        locate(DualListBox, "Location Type", "Retail").scrollIntoView(true);
        clickOn(locate(Arrow, "Location Type", "Chosen"));
        locate(Input, "Foreign Currency - Qty Connotes", "1").scrollIntoView(true);
    }

    public LocationType(WebDriver driver) {
        super(driver);
    }
}
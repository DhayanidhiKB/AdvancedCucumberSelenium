package com.my.salesforce.sandbox.application.pages.opportunities.related.csq.new_freight;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.related.csq.new_freight.PremiumElements;
import org.openqa.selenium.WebDriver;

public class TimeDetails extends BasePage implements PremiumElements {
    public void time_details(String current_arrival_time, String required_arrival_time,
                             String closing_time, String ready_time, String loading_time) {
        setValue(current_arrival_time, locate(ComboBox, "Current vehicle arrival time"));
        setValue(required_arrival_time, locate(Input, "Required vehicle arrival time", "1"));
        setValue(closing_time, locate(ComboBox, "Closing time"));
        clickOn(locate(Input, "Required vehicle arrival time", "1"));
        setValue(ready_time, locate(ComboBox, "Freight ready time"));
        setValue(loading_time, locate(Input, "Approx loading time (Minutes)", "1"));
    }

    public TimeDetails(WebDriver driver) {
        super(driver);
    }
}
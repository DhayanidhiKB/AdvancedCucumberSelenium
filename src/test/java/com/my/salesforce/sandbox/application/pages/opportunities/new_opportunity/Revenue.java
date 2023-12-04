package com.my.salesforce.sandbox.application.pages.opportunities.new_opportunity;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity.RevenueElements;
import org.openqa.selenium.WebDriver;

public class Revenue extends BasePage implements RevenueElements {
    public Revenue is_ready() {
        isItVisible(Header);
        return this;
    }

    public void enter_total_value_as(String value) {
        setValue(value, TotalOpportunityValue);
    }

    public Revenue(WebDriver driver) {
        super(driver);
    }
}
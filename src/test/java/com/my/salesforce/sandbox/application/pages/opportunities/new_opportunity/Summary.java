package com.my.salesforce.sandbox.application.pages.opportunities.new_opportunity;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity.SummaryElements;
import org.openqa.selenium.WebDriver;

public class Summary extends BasePage implements SummaryElements {
    public void choose_type_as(String type) {
        Header.scrollIntoView(true);
        clickOn(Type);
        clickOn(locate(Option, type));
    }

    public Summary(WebDriver driver) {
        super(driver);
    }
}
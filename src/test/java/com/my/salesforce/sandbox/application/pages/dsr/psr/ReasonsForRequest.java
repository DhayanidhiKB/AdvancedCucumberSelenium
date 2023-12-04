package com.my.salesforce.sandbox.application.pages.dsr.psr;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.PricingSupportRequestPageElements;
import org.openqa.selenium.WebDriver;

public class ReasonsForRequest extends BasePage implements PricingSupportRequestPageElements {
    public void enter(String annualRevenue, String annualVolume, String catalyst,
                      String salesJustification) {
        setValue(annualRevenue, locate(Input, "Estimated annual revenue"));
        setValue(annualVolume, locate(Input, "Estimated annual volume"));
        mouseOverAndSelect(catalyst, locate(ListBox, "Catalyst"));
        setValue(salesJustification, locate(TextArea, "Sales Justification"));
    }

    public ReasonsForRequest(WebDriver driver) {
        super(driver);
    }
}
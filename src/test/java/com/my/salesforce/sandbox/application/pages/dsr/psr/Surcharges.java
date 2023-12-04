package com.my.salesforce.sandbox.application.pages.dsr.psr;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.PricingSupportRequestPageElements;
import org.openqa.selenium.WebDriver;

public class Surcharges extends BasePage implements PricingSupportRequestPageElements {
    public void choose() {
        clickOn(locate(DualListBox, "Surcharges", "Administrative fee"));
        clickOn(locate(Arrow, "Surcharges", "Chosen"));
        clickOn(locate(DualListBox, "Surcharges", "Fuel surcharge"));
        clickOn(locate(Arrow, "Surcharges", "Chosen"));
        clickOn(locate(DualListBox, "Surcharges", "Manual handling surcharge"));
        clickOn(locate(Arrow, "Surcharges", "Chosen"));
        clickOn(locate(DualListBox, "Surcharges", "Return to sender"));
        clickOn(locate(Arrow, "Surcharges", "Chosen"));
        clickOn(locate(DualListBox, "Surcharges", "Security management charge"));
        clickOn(locate(Arrow, "Surcharges", "Chosen"));
        clickOn(locate(DualListBox, "Surcharges", "Unmanifested article"));
        clickOn(locate(Arrow, "Surcharges", "Chosen"));
    }

    public Surcharges(WebDriver driver) {
        super(driver);
    }
}
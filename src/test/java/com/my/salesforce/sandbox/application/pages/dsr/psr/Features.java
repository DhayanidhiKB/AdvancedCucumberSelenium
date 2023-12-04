package com.my.salesforce.sandbox.application.pages.dsr.psr;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.PricingSupportRequestPageElements;
import org.openqa.selenium.WebDriver;

public class Features extends BasePage implements PricingSupportRequestPageElements {
    public void choose() {
        clickOn(locate(DualListBox, "Features", "Signature on delivery"));
        clickOn(locate(Arrow, "Features", "Chosen"));
        clickOn(locate(DualListBox, "Features", "Capture ID"));
        clickOn(locate(Arrow, "Features", "Chosen"));
        clickOn(locate(DualListBox, "Features", "Wine and Alcohol"));
        clickOn(locate(Arrow, "Features", "Chosen"));
    }

    public Features(WebDriver driver) {
        super(driver);
    }
}
package com.my.salesforce.sandbox.application.pages.dsr.psr;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.PricingSupportRequestPageElements;
import org.openqa.selenium.WebDriver;

public class Speed extends BasePage implements PricingSupportRequestPageElements {
    public Speed enable_returns() {
        mouseOverAndClickOn(Returns);
        return this;
    }

    public void choose() {
        clickOn(locate(DualListBox, "Speed", "Standard"));
        clickOn(locate(Arrow, "Speed", "Chosen"));
        clickOn(locate(DualListBox, "Speed", "Premium"));
        clickOn(locate(Arrow, "Speed", "Chosen"));
    }

    public void set_transit_cover(String option, String amount) {
        isItInteractable(locate(ListBox, "Transit Cover Type"));
        mouseOverAndSelect(option, locate(ListBox, "Transit Cover Type"));
        clear(locate(Input, "Min Transit Cover Amount Per Article"));
        setValue(amount, locate(Input, "Min Transit Cover Amount Per Article"));
    }

    public Speed(WebDriver driver) {
        super(driver);
    }
}
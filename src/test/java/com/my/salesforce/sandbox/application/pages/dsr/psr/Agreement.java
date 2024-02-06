package com.my.salesforce.sandbox.application.pages.dsr.psr;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.PricingSupportRequestPageElements;
import org.openqa.selenium.WebDriver;

public class Agreement extends BasePage implements PricingSupportRequestPageElements {
    public void set(String agreementDuration, String agreementType) {
        isItInteractable(EntityName).scrollIntoView(true);
        isItInteractable(locate(ListBox, "Agreement Duration"));
        mouseOverAndSelect(agreementDuration, locate(ListBox, "Agreement Duration"));
        locate(ListBox, "Agreement Duration").scrollIntoView(true);
        mouseOverAndSelect(agreementType, locate(ListBox, "Multi-Year Agreement Type"));
    }

    public Agreement(WebDriver driver) {
        super(driver);
    }
}
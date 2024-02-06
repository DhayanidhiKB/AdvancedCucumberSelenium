package com.my.salesforce.sandbox.application.pages.dsr.customer_onboarding;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.CustomerOnboardingDSRPageElements;
import org.openqa.selenium.WebDriver;

public class ProductListBox extends BasePage implements CustomerOnboardingDSRPageElements {
    public void choose(String product) {
        clickOn(locate(DualListBox, "Available", "APGL Commercial Outbound"));
        clickOn(locate(DualListBox, "Available", product));
        clickOn(locate(Arrow, "Available", "Chosen"));
    }

    public ProductListBox(WebDriver driver) {
        super(driver);
    }
}
package com.my.salesforce.sandbox.application.pages.opportunities.new_opportunity;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity.LegalEntityNameElements;
import org.openqa.selenium.WebDriver;

public class LegalEntityName extends BasePage implements LegalEntityNameElements {
    public LegalEntityName is_ready() {
        isItVisible(Header);
        return this;
    }

    public LegalEntityName select(String org_name) {
        clickOn(locate(Link, org_name));
        return this;
    }

    public LegalEntityName(WebDriver driver) {
        super(driver);
    }
}
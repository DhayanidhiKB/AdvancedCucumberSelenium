package com.my.salesforce.sandbox.application.pages.contract;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.AgreementProductAttributeValueElements;
import org.openqa.selenium.WebDriver;

public class AgreementProductAttributeValue extends BasePage implements AgreementProductAttributeValueElements {
    public AgreementProductAttributeValue is_ready() {
        isItVisible(Header);
        isItVisible(locate(Label, "Attribute Value Id"));
        return this;
    }

    public AgreementProductAttributeValue validate_tier(String tier) {
        shouldHaveText(locate(GetText, "Tier (ex Gst)"), tier);
        return this;
    }

    public AgreementProductAttributeValue validate_zone(String price_structure) {
        shouldHaveText(locate(GetText, "Price Structure"), price_structure);
        return this;
    }

    public void validate_zones(String zone, String zone1, String zone2) {
        shouldHaveText(locate(GetText, "Selected Lodgement Zone"), zone);
        shouldHaveText(locate(GetText, "Additional Lodgement Zone 1"), zone1);
        shouldHaveText(locate(GetText, "Additional Lodgement Zone 2"), zone2);
    }

    public AgreementProductAttributeValue(WebDriver driver) {
        super(driver);
    }
}
package com.my.salesforce.sandbox.application.pages.opportunities.related.csq.new_freight;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.related.csq.new_freight.NewFreightOfferingElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class NewFreightOffering extends BasePage implements NewFreightOfferingElements {
    public NewFreightOffering is_it_ready() {
        isItVisible(Header);
        isItInteractable(locate(Button, "Next"));
        return this;
    }

    public NewFreightOffering choose_record_type(String record_type) {
        clickOn(locate(RecordType, record_type));
        clickOn(locate(Button, "Next"));
        return this;
    }

    private Premium premium;

    public NewFreightOffering(WebDriver driver) {
        super(driver);
        setPremium(new Premium(lDriver));
    }
}
package com.my.salesforce.sandbox.application.pages.shopping_cart.eparcel;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.eparcel.RateCardKeyElements;
import org.openqa.selenium.WebDriver;

public class RateCardKey extends BasePage implements RateCardKeyElements {
    public RateCardKey is_ready() {
        isItInteractable(locate(Button, "Continue"));
        isItInteractable(locate(Button, "Cancel"));
        isItInteractable(RateCardKey);
        isItVisible(Header);
        return this;
    }

    public void enter(String rk_code) {
        setValue(rk_code, RateCardKey);
        clickOn(locate(Button, "Continue"));
        isItVisible(Warning);
        clickOn(Confirm);
        isItInteractable(ConfirmationPopUp);
        clickOn(locate(Confirmation, "Yes"));
    }

    public RateCardKey(WebDriver driver) {
        super(driver);
    }
}
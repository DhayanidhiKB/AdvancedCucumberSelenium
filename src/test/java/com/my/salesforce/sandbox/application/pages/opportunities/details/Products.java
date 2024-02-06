package com.my.salesforce.sandbox.application.pages.opportunities.details;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.details.ProductsElements;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Products extends BasePage implements ProductsElements {
    public Products is_ready() {
        isItVisible(Header);
        isItInteractable(ProductsLink);
        isItInteractable(OpportunitiesLink);
        isItInteractable(SelectAll);
        return this;
    }

    public Products edit(String quantity, String total_price) {
        clickOn(EditQuantity);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        setValue(quantity, EditQuantity);
        clickOn(EditUnitSales);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        setValue(total_price, EditUnitSales);
        return this;
    }

    public Products save() {
        isItInteractable(Cancel);
        clickOn(Save);
        is_ready();
        return this;
    }

    public Products(WebDriver driver) {
        super(driver);
    }
}
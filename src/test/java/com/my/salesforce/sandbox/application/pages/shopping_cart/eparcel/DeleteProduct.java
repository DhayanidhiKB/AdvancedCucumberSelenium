package com.my.salesforce.sandbox.application.pages.shopping_cart.eparcel;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.eparcel.EParcelProductAttributesPageElements;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

public class DeleteProduct extends BasePage implements EParcelProductAttributesPageElements {
    public void deleteTheProduct(String productName) {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        ProgressBar.shouldBe(disappear).shouldBe(hidden).shouldHave(hidden);
        isItInteractable(MoreMenu);
        clickOn(locate(CheckBox, productName));
        clickOn(Delete);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        ProgressBar.shouldBe(disappear).shouldBe(hidden).shouldHave(hidden);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        clickOn(MoreMenu);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        clickOn(locate(Product, "Checkout"));
    }

    public DeleteProduct(WebDriver driver) {
        super(driver);
    }
}
package com.my.salesforce.sandbox.application.pages.opportunities.details.edit_products;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.details.edit_products.EditElements;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Edit extends BasePage implements EditElements {
    public Edit quantity() {
        actions().click(EditQuantity.get(0)).pause(Duration.ofSeconds(1)).build().perform();
        clear(InputQuantity);
        clickOn(QuantityIcon);
        clickOn(Save);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        isItVisible(QuantityError);
        clickOn(Cancel);
        return this;
    }

    public void quantity(String quantity) {
        isItInteractable(EditQuantity.get(0));
        actions().click(EditQuantity.get(0)).pause(Duration.ofSeconds(2)).build().perform();
        clear(InputQuantity);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        type(quantity, InputQuantity);
        clickOn(QuantityIcon);
    }

    public Edit start_date() {
        actions().click(EditRevenueStartDate.get(0)).pause(Duration.ofSeconds(1)).build().perform();
        clear(InputStartDate);
        clickOn(StartDateIcon);
        clickOn(Save);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        isItVisible(StartDateError);
        clickOn(Cancel);
        return this;
    }

    public void start_date(String date) {
        isItInteractable(EditRevenueStartDate.get(0));
        actions().click(EditRevenueStartDate.get(0)).pause(Duration.ofSeconds(1)).build().perform();
        clear(InputStartDate);
        type(date, InputStartDate);
        clickOn(StartDateIcon);
    }

    public Edit end_date() {
        actions().click(EditRevenueEndDate.get(0)).pause(Duration.ofSeconds(1)).build().perform();
        InputEndDate.shouldBe(visible).shouldBe(enabled).clear();
        EndDateIcon.click();
        Save.shouldBe(visible).shouldBe(enabled).click();
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        EndDateError.shouldBe(visible).shouldHave(appear);
        Cancel.shouldBe(visible).shouldBe(enabled).click();
        return this;
    }

    public void end_date(String date) {
        isItInteractable(EditRevenueEndDate.get(0));
        actions().click(EditRevenueEndDate.get(0)).pause(Duration.ofSeconds(1)).build().perform();
        clear(InputEndDate);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        type(date, InputEndDate);
        clickOn(EndDateIcon);
    }

    public void unit_sales(String unit_sales) {
        isItVisible(EditUnitSales.get(0));
        actions().click(EditUnitSales.get(0)).pause(Duration.ofSeconds(2)).build().perform();
        clear(InputUnitSales);
        type(unit_sales, InputUnitSales);
        clickOn(UnitSalesIcon);
    }

    public void save() {
        isItInteractable(Cancel);
        clickOn(Save);
    }

    public Edit(WebDriver driver) {
        super(driver);
    }
}
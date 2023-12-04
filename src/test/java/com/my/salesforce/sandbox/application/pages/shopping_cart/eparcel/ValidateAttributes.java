package com.my.salesforce.sandbox.application.pages.shopping_cart.eparcel;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.eparcel.EParcelProductAttributesPageElements;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class ValidateAttributes extends BasePage implements EParcelProductAttributesPageElements {
    public void validate(@NotNull String productName, String categoryOrIndustry,
                         String priceOrCustomer, String postcodeOrRevenue,
                         String amountOrPlatforms) {
        switch (productName) {
            case "eParcel":
                selectCategoryOrIndustry(categoryOrIndustry);
                selectPriceOrCustomer(priceOrCustomer);
                fill(postcodeOrRevenue, amountOrPlatforms);
                break;
            case "eParcel Express":
                selectCategoryOrIndustry(categoryOrIndustry);
                isItInteractable(locate(ExpressPriceStructure, priceOrCustomer));
                fill(postcodeOrRevenue, amountOrPlatforms);
                break;
            case "International Digital PCMS Bundle incl Airmail Letters", "International Digital PCMS Bundle":
                selectCategoryOrIndustry(categoryOrIndustry);
                selectPriceOrCustomer(priceOrCustomer);
                clickOn(locate(ListBox, "4"));
                mouseOverAndClickOn(locate(ListBoxMenu, postcodeOrRevenue));
                clickOn(locate(ListBox, "5"));
                mouseOverAndClickOn(locate(ListBoxMenu, amountOrPlatforms));
                break;
        }
        isItInteractable(Validate);
        actions().moveToElement(Validate).click().pause(Duration.ofSeconds(15)).build().perform();
    }

    private void selectCategoryOrIndustry(String categoryOrIndustry) {
        clickOn(locate(ListBox, "1"));
        mouseOverAndClickOn(locate(ListBoxMenu, categoryOrIndustry));
    }

    private void selectPriceOrCustomer(String priceOrCustomer) {
        clickOn(locate(ListBox, "2"));
        mouseOverAndClickOn(locate(ListBoxMenu, priceOrCustomer));
    }

    private void fill(String postcodeOrRevenue, String amountOrPlatforms) {
        setValue(postcodeOrRevenue, LodgementPostcode);
        clickOn(TransitCover);
        actions().sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE)
                .sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE)
                .sendKeys(amountOrPlatforms).build().perform();
    }

    public ValidateAttributes(WebDriver driver) {
        super(driver);
    }
}
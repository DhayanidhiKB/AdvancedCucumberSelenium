package com.my.salesforce.sandbox.application.pages.contract.dov;

import com.codeborne.selenide.Selenide;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.dov.DOVCategoryElements;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DOVCategory extends BasePage implements DOVCategoryElements {
    public DOVCategory is_ready() {
        switch_to_frame(iFrame);
        isItVisible(Header);
        isItInteractable(locate(DropDown, "Category"));
        isItInteractable(locate(Button, "Continue"));
        isItInteractable(locate(Button, "Cancel"));
        return this;
    }

    //Parameters: Proposal and Contract Flow / Increase Revenue
    public DOVCategory enter(String variation_category, String revenue_type) {
        locate(DropDown, "Category").selectOptionContainingText(variation_category);
        executeJavaScript("window.scrollBy(0,100)");
        isItInteractable(locate(DropDown, "Revenue Type"));
        locate(DropDown, "Revenue Type").selectOptionContainingText(revenue_type);
        isItInteractable(ExistingOpportunity);
        clickOn(locate(Button, "Continue"));
        return this;
    }

    public void back_to_shopping_cart() {
        locate(Button, "Cancel").should(disappear).shouldHave(disappear);
        Header.should(disappear).shouldHave(disappear);
        ExistingOpportunity.should(disappear).shouldHave(disappear);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(45));
        Selenide.switchTo().defaultContent();
    }

    public DOVCategory(WebDriver driver) {
        super(driver);
    }
}
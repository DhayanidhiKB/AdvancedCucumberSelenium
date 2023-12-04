package com.my.salesforce.sandbox.application.pages.contract.renewal;

import com.codeborne.selenide.Selenide;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.renew.RenewContractElements;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

public class RenewContract extends BasePage implements RenewContractElements {
    public RenewContract is_ready() {
        switch_to_frame(iFrame);
        isItInteractable(locate(DropDown, "Category"));
        isItInteractable(locate(Input, "Renew"));
        isItInteractable(locate(Input, "Cancel"));
        return this;
    }

    //Parameters: Standard Apttus Renewal
    public RenewContract renew(String renew_category) {
        locate(DropDown, "Category").selectOptionContainingText(renew_category);
        isItInteractable(ExistingOpportunity);
        clickOn(locate(Input, "Renew"));
        return this;
    }

    public void back_to_shopping_cart() {
        locate(Input, "Cancel").shouldBe(disappear).shouldHave(disappear);
        Header.shouldBe(disappear).shouldHave(disappear);
        ExistingOpportunity.shouldBe(disappear).shouldHave(disappear);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(35));
        Selenide.switchTo().defaultContent();
    }

    public RenewContract(WebDriver driver) {
        super(driver);
    }
}
package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.new_subaccount.request_details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount.NewSubAccountRequestPageElements;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ParcelSend extends BasePage implements NewSubAccountRequestPageElements {
    public void set(String parcel_send, String sub_account_contract) {
        clickOn(locate(DropDown, "Is Parcel Send Login Required?"));
        clickOn(locate(Option, "Is Parcel Send Login Required?", parcel_send));
        if (parcel_send.equals("Yes")) {
            actions().moveToElement($(SubAccountContracts)).click().sendKeys(sub_account_contract)
                    .pause(Duration.ofSeconds(5)).sendKeys(Keys.ARROW_DOWN).pause(Duration.ofSeconds(1))
                    .sendKeys(Keys.ARROW_DOWN).pause(Duration.ofSeconds(1))
                    .sendKeys(Keys.ENTER).pause(Duration.ofSeconds(1)).build().perform();
        }
    }

    public ParcelSend set(String parcel_send) {
        clickOn(locate(DropDown, "Is Parcel Send Login Required?"));
        clickOn(locate(Option, "Is Parcel Send Login Required?", parcel_send));
        return this;
    }

    public ParcelSend verify(String sub_account_contract) {
        locate("//label[text()='Sub Account Contact']/following-sibling::div/descendant::input[@data-value='%value%']", sub_account_contract)
                .shouldBe(visible).shouldBe(appear);
        return this;
    }

    public ParcelSend(WebDriver driver) {
        super(driver);
    }
}
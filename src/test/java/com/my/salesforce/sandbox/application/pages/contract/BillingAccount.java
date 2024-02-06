package com.my.salesforce.sandbox.application.pages.contract;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.BillingAccountPageElements;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class BillingAccount extends BasePage implements BillingAccountPageElements {
    public com.my.salesforce.sandbox.application.pages.contract.BillingAccount is_ready() {
        $(Header).shouldBe(visible).shouldHave(appear);
        locate(Edit, "Source System").shouldBe(visible).shouldBe(enabled).shouldBe(interactable).shouldHave(appear);
        return this;
    }

    public void verify(String billing_account, String lodgement_point) {
        locate(BillingAccount, billing_account).shouldHave(visible).shouldHave(appear);
        locate(Edit, "Credit Representative Email").scrollIntoView(true);
        locate(GetText, "APPC Primary Lodgement Point").shouldHave(visible).shouldHave(appear)
                .shouldHave(text(lodgement_point));
    }

    public BillingAccount(WebDriver driver) {
        super(driver);
    }
}
package com.my.salesforce.sandbox.application.pages.organisations.details;

import com.codeborne.selenide.Selenide;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.details.CreditLimitElements;
import org.openqa.selenium.WebDriver;

public class CreditLimit extends BasePage implements CreditLimitElements {
    public void enter(String credit_limit) {
        mouseOverAndClickOn(EditSTCreditLimit);
        setValue(credit_limit, EnterSTCreditLimit);
        clickOn(Save);
        isItInteractable(EnterSTCreditLimit);
        shouldHaveText(STCreditLimit, "$" + credit_limit + ".00");
        Selenide.refresh();
    }

    public CreditLimit(WebDriver driver) {
        super(driver);
    }
}
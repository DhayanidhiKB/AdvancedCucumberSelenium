package com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface SubAccountListPageElements {
    SelenideElement Header = $(By.xpath("//span[text()='Sub Account List']/parent::h2"));
    String Button = "(//button[text()='%value1%'])[%value2%]";
    SelenideElement SelectAll = $(By.xpath("//span[text()='Select All']/parent::label"));
    SelenideElement FinalizeRequest = $(By.xpath("//button[text()='Finalize Request(s)']/parent::lightning-button"));
    SelenideElement SubHeader = $(By.xpath("//h2[text()='Submit Sub Account Request']"));
    SelenideElement SuccessMessage = $(By.xpath("//b[text()='Sub-Account creation Request submitted Successfully. You may close this window now.']"));
    SelenideElement Close = $(By.xpath("//footer[@class='slds-modal__footer']/button[@title='Close']"));
}
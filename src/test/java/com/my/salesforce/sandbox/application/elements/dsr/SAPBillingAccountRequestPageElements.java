package com.my.salesforce.sandbox.application.elements.dsr;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public interface SAPBillingAccountRequestPageElements {
    SelenideElement Header = $(By.xpath("//b[contains(text(),'SAP Billing Account')]/parent::h2"));
    SelenideElement Message = $(By.xpath("//span[text()='What sender name should be displayed on parcel labels']"));
    String Button = "//button[text()='%value%']";
    SelenideElement Close = $(By.xpath("//footer[@class='slds-modal__footer']/button[text()='Close']"));
}
package com.my.salesforce.sandbox.application.elements.dsr;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface NewDealSupportRequestPageElements {
    SelenideElement Header = $(By.xpath("//h2[text()='New Deal Support Request']"));
    SelenideElement SubHeader = $(By.xpath("//span[text()='Select the Deal Support Request Type']"));
    SelenideElement RequestTypeBody = $(By.xpath("//div[@class='quick-actions-panel']//div[@class='slds-form-element__control']"));
    String RequestType = "//b[text()='%value%']/ancestor::label/span[@class='slds-radio_faux']";
    SelenideElement Next = $(By.xpath("//button[text()='Next']/parent::lightning-button"));
}
package com.my.salesforce.sandbox.application.elements.organisations.billing_accounts;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface BillingAccountDetailsPageElements {
    SelenideElement Header = $(By.xpath("//div[text()='Billing Account']"));
    SelenideElement SubAccountRequest = $(By.xpath("//button[@name='Billing_Account__c.Sub_Account_Request']/ancestor::li"));
    SelenideElement MoreActions = $(By.xpath("//span[text()='Show more actions']/ancestor::lightning-button-menu[contains(@data-target-reveals,'StandardButton.Billing_Account__c')]"));
    SelenideElement BillingAccountNumber = $(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Billing_Account__c.LEGACY_ID__c']/descendant::lightning-formatted-text"));
    String Customer = "//div[@data-target-selection-name='sfdc:RecordField.Billing_Account__c.Organisation__c']/descendant::span[text()='%value%']";
    SelenideElement ABN = $(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Billing_Account__c.ABN__c']/descendant::lightning-formatted-text"));
    SelenideElement RelatedTab = $(By.xpath("(//a[@data-label='Related']/parent::li)[2]"));
}
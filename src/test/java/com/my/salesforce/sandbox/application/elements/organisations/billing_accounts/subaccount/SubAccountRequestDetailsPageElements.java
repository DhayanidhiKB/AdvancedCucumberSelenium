package com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface SubAccountRequestDetailsPageElements {
    SelenideElement Header = $(By.xpath("//div[text()='Sub Account Request']/parent::h1"));
    SelenideElement NewNote = $(By.xpath("//li[@data-target-selection-name='sfdc:QuickAction.FeedItem.ContentNote']"));
    SelenideElement SubmitForApproval = $(By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.APT_Sub_Account__c.Submit']"));
    String Verify = "//span[text()='%value%']/parent::div/following-sibling::div/descendant::lightning-formatted-text";
}
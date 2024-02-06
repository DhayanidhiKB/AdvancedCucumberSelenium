package com.my.salesforce.sandbox.application.elements.organisations.billing_accounts;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface BillingAccountRelatedTabElements {
    SelenideElement AuthorizationRules = $(By.xpath("//span[text()='Authorization Rules']/parent::a/parent::h2"));
    SelenideElement NewAuthorizationRule = $(By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.Authorization_Rule__c.New']"));
    SelenideElement Files = $(By.xpath("//span[text()='Files']/parent::a/parent::h2"));
    SelenideElement SubAccountRequests = $(By.xpath("//span[text()='Sub Account Requests']/parent::a/parent::h2"));
    SelenideElement ViewAllSubAccountRequests = $(By.xpath("//span[text()='Sub Account Requests']/parent::span[text()='View All']"));
}
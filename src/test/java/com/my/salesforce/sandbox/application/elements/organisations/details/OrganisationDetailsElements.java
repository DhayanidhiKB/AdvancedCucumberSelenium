package com.my.salesforce.sandbox.application.elements.organisations.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface OrganisationDetailsElements {
    SelenideElement LegalEntityName = $(By.xpath("//records-record-layout-item[@field-label='Legal Entity Name']//lightning-formatted-text"));
    SelenideElement ABN = $(By.xpath("//p[text()='ABN']/following-sibling::p/descendant::lightning-formatted-text"));
    SelenideElement RecordType = $(By.xpath("//span[text()='Record Type']/parent::div/following-sibling::div/descendant::span[text()='Organisation']"));
    SelenideElement Related = $(By.xpath("//a[text()='Related']/parent::li"));
    SelenideElement DealSupportRequests = $(By.xpath("//a[text()='Deal Support Requests']"));
    SelenideElement EditLegalEntityName = $(By.xpath("//button[@title='Edit Legal Entity Name']"));
}
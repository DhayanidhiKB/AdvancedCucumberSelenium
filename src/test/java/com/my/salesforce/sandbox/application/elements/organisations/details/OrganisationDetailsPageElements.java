package com.my.salesforce.sandbox.application.elements.organisations.details;

import org.openqa.selenium.By;

public interface OrganisationDetailsPageElements {
    By LegalEntityName = By.xpath("//records-record-layout-item[@field-label='Legal Entity Name']//lightning-formatted-text");
    By ABN = By.xpath("//p[text()='ABN']/following-sibling::p/descendant::lightning-formatted-text");
    By RecordType = By.xpath("//span[text()='Record Type']/parent::div/following-sibling::div/descendant::span[text()='Organisation']");
    By ChangeAddress = By.xpath("//a[text()='Change Address']");
    By DealSupportRequests = By.xpath("//a[text()='Deal Support Requests']");
    By EditLegalEntityName = By.xpath("//button[@title='Edit Legal Entity Name']");
    By EditABN = By.xpath("//button[@title='Edit ABN']");
}
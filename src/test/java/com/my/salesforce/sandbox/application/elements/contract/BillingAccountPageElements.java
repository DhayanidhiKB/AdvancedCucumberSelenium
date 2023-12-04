package com.my.salesforce.sandbox.application.elements.contract;

import org.openqa.selenium.By;

public interface BillingAccountPageElements {
    By Header = By.xpath("//h1[text()='Billing Accounts']");
    By SelectAll = By.xpath("//span[text()='Select All']/ancestor::span");
    String BillingAccount = "//lightning-formatted-text[text()='SAP ERP']//ancestor::records-record-layout-row/following-sibling::records-record-layout-row//lightning-formatted-text[text()='%value%']";
    String Edit = "//button[@title='Edit %value%']";
    String GetText = "//span[text()='%value%']/parent::div/following-sibling::div//lightning-formatted-text";
}
package com.my.salesforce.sandbox.application.elements.contract;

import org.openqa.selenium.By;

public interface SAPContractPageElements {
    By Header = By.xpath("//div[text()='SAP Contract']/parent::h1");
    By PrintableView = By.xpath("//button[@name='PrintableView']");
    String Text = "//span[text()='%value1%']/parent::div/following-sibling::div//lightning-formatted-text[text()='%value2%']";
    String Edit = "//button[@title='Edit %value%']";
    String Search = "//input[@placeholder='%value%']";
    String GetText = "//span[text()='%value%']/parent::div/following-sibling::div//lightning-formatted-text";
    By EditBillingAccount = By.xpath("//button[@title='Edit SAP Contract Number']//ancestor::records-record-layout-row/following-sibling::records-record-layout-row//button[@title='Edit Billing Account']");
    By BillingAccountLink = By.xpath("(//button[@title='Edit SAP Contract Number']//ancestor::records-record-layout-row/following-sibling::records-record-layout-row//a//span)[1]");
    By Cancel = By.xpath("//button[@name='CancelEdit']/ancestor::li");
}
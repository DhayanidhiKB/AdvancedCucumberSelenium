package com.my.salesforce.sandbox.application.elements.contract.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface SAPContractPageElements {
    SelenideElement Header = $(By.xpath("//div[text()='SAP Contract']/parent::h1"));
    SelenideElement PrintableView = $(By.xpath("//button[@name='PrintableView']"));
    String Text = "//span[text()='%value1%']/parent::div/following-sibling::div//lightning-formatted-text[text()='%value2%']";
    String Edit = "//button[@title='Edit %value%']";
    String Search = "//input[@placeholder='%value%']";
    String GetText = "//span[text()='%value%']/parent::div/following-sibling::div//lightning-formatted-text";
    SelenideElement EditBillingAccount = $(By.xpath("//button[@title='Edit SAP Contract Number']//ancestor::records-record-layout-row/following-sibling::records-record-layout-row//button[@title='Edit Billing Account']"));
    SelenideElement BillingAccountLink = $(By.xpath("(//button[@title='Edit SAP Contract Number']//ancestor::records-record-layout-row/following-sibling::records-record-layout-row//a//span)[1]"));
    SelenideElement Cancel = $(By.xpath("//button[@name='CancelEdit']/ancestor::li"));
}
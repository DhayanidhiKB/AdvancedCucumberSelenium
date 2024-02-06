package com.my.salesforce.sandbox.application.elements.contract;

import org.openqa.selenium.By;

public interface ManageContractRelationshipsPageElements {
    By Header = By.xpath("//span[text()='Manage Contract Relationships']/parent::h2");
    By SubHeader = By.xpath("//div[text()='Please select one of the options below']");
    String Button = "//button[text()='%value%']";
    String RadioOption = "//span[text()='%value%']/preceding-sibling::span";
    By AppcProduct = By.xpath("//lightning-base-formatted-text[text()='Australia Post Parcel Contract']/ancestor::th/preceding-sibling::td/lightning-primitive-cell-checkbox");
    By SelectAll = By.xpath("//span[text()='Select All']/parent::label");
    String BillingAccount = "//a[text()='%value%']/ancestor::th/preceding-sibling::td//span[@class='slds-checkbox']";
    By SearchLodgementPoint = By.xpath("//input[@placeholder='Search Lodgement Point']");
    String SearchOption = "//span[text()='%value%']";
    String LodgementPointName = "//div[text()='%value%']";
    By TableTitle = By.xpath("//span[text()='Added Billing Accounts']");
    By AddedBillingAccount = By.xpath("//a[contains(text(),'ALP-')]/ancestor::th");
    String lodgementPointName = "//td[@data-label='Lodgement Point Name']/child::div[text()='%value%']";
}
package com.my.salesforce.sandbox.application.elements.contract.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface ManageContractRelationshipsPageElements {
    SelenideElement Header = $(By.xpath("//span[text()='Manage Contract Relationships']/parent::h2"));
    SelenideElement SubHeader = $(By.xpath("//div[text()='Please select one of the options below']"));
    String Button = "//button[text()='%value%']";
    String RadioOption = "//span[text()='%value%']/preceding-sibling::span";
    SelenideElement AppcProduct = $(By.xpath("//lightning-base-formatted-text[text()='Australia Post Parcel Contract']/ancestor::th/preceding-sibling::td/lightning-primitive-cell-checkbox"));
    By SelectAll = By.xpath("//span[text()='Select All']/parent::label");
    SelenideElement SearchLodgementPoint = $(By.xpath("//input[@placeholder='Search Lodgement Point']"));
    String SearchOption = "//span[text()='%value%']";
    String LodgementPointName = "//div[text()='%value%']";
    SelenideElement TableTitle = $(By.xpath("//span[text()='Added Billing Accounts']"));
    By AddedBillingAccount = By.xpath("//a[contains(text(),'ALP-')]/ancestor::th");
}
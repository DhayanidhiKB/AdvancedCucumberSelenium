package com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface NewSubAccountRequestPageElements {
    SelenideElement iFrame = $(By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]"));
    SelenideElement Header = $(By.xpath("//div[text()='New Sub Account Request']"));
    String SubHeader = "//span[text()='%value%']/ancestor::h2";
    String Labels = "//b[contains(text(),'%value%')]/parent::label";
    SelenideElement ParentBillingAccountNumber = $(By.xpath("//b[contains(text(),'Billing Account Number')]/parent::label"));
    SelenideElement SubAccountRequestSource = $(By.xpath("//b[contains(text(),'Sub Account Request')]/parent::label"));
    String Enter = "//label[text()='%value%']/following-sibling::div//input";
    String DropDown = "//label[text()='%value%']/following-sibling::div//button";
    String Option = "//label[text()='%value1%']/following-sibling::div//lightning-base-combobox-item//span[@title='%value2%']";
    SelenideElement SubAccountContracts = $(By.xpath("//label[text()='Sub Account Contact']/following-sibling::div/descendant::input"));
    String DualListBox = "//div[text()='%value1%']/following-sibling::div/descendant::ul/li//span[@title='%value2%']";
    String Arrow = "//div[text()='%value1%']/following-sibling::div/descendant::button[@title='Move selection to %value2%']";
    String Verify = "//b[contains(text(),'%value1%')]/parent::label[text()='%value2%']";
    String Button = "//button[text()='%value%']";
    SelenideElement AddressToggle = $(By.xpath("//input[@name='senderAddressToggle']/parent::label"));
    SelenideElement Confirm = $(By.xpath("//span[text()='Confirm']/parent::button"));
    SelenideElement Confirmed = $(By.xpath("//span[text()='Confirmed']/parent::button"));
    SelenideElement SearchLodgementPoint = $(By.xpath("//input[@placeholder='Search Lodgement Point']"));
    String SearchOption = "//span[text()='%value%']";
    SelenideElement Alert = $(By.xpath("//h2[text()='Create an Opportunity']"));
    SelenideElement CreateOpportunityMessage = $(By.xpath("//b[text()='To apply rates at a billing account for APPC, please create an opportunity post successful sub account creation.']"));
}
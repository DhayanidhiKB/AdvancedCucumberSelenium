package com.my.salesforce.sandbox.application.elements.dsr.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface DealSupportRequestDetailsPageElements {
    SelenideElement Header = $(By.xpath("//div[text()='Deal Support Request']/parent::h1"));
    String Name = "(//div[text()='Deal Support Request']/parent::h1/descendant::lightning-formatted-text)[%value%]";
    String Message = "//b[text()='%value%']";
    SelenideElement More = $(By.xpath("//div[text()='Deal Support Request']/ancestor::div[contains(@class,'primaryFieldRow')]/descendant::li/descendant::lightning-button-menu"));
    String MoreLinks = "//runtime_platform_actions-action-renderer[@title='%value%']";
    String SubmitButton = "//h2[text()='Submit']/parent::header/following-sibling::footer//span[text()='%value%']";
    String CurrentStage = "//li[@data-name='%value%' and contains(@class,'slds-is-current slds-is-active')]";
    SelenideElement WillBeSubmittedMessage = $(By.xpath("//span[text()='Support Request will be submitted to Request Support Team']"));
    String Link = "//button[text()='%value%']/ancestor::li";
    String Owner = "//span[text()='Owner']/parent::div/following-sibling::div/descendant::slot[text()='%value% Queue']/parent::span";
    String Status = "(//span[text()='Status']/parent::div/following-sibling::div/descendant::lightning-formatted-text[text()='%value%'])[1]";
    SelenideElement Product = $(By.xpath("//span[text()='Product']/parent::div/following-sibling::div/descendant::lightning-formatted-text"));
    String Edit = "//button[@title='Edit %value%']";
    String Input = "//label[text()='%value%']/following-sibling::div/child::input";
    String TextArea = "//label[text()='%value%']/following-sibling::div/child::textarea";
    String ListBox = "//label[text()='%value%']/following-sibling::div";
    String ListMenus = "//lightning-base-combobox-item//span[@title='%value%']";
    String Date = "//label[text()='%value%']/following-sibling::div/input";
    SelenideElement Save = $(By.xpath("//button[text()='Save']"));
    String Complete = "//span[text()='%value1%']/parent::span/following-sibling::div//a[text()='%value2%']";
    SelenideElement CompleteDSR = $(By.xpath("//footer/descendant::span[text()='Save']"));
    String Verify = "//span[text()='%value1%']/parent::div/following-sibling::div//lightning-formatted-text[text()='%value2%']";
    String GetText = "//span[text()='%value%']/parent::div/following-sibling::div//lightning-formatted-text";
}
package com.my.salesforce.sandbox.application.elements.dsr;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface CustomerOnboardingDSRPageElements {
    SelenideElement Header = $(By.xpath("//h2[contains(text(),'Edit DS-')]"));
    SelenideElement CustomerContact = $(By.xpath("//label[text()='Customer Contact']/following-sibling::div/descendant::input"));
    String DualListBox = "//span[text()='%value1%']/following-sibling::div/descendant::div[@data-value='%value2%']";
    String Arrow = "//span[text()='%value1%']/parent::div/following-sibling::div/descendant::button[@title='Move selection to %value2%']";
    String TextField = "//label[text()='%value%']/following-sibling::div/input";
    String TextArea = "//label[text()='%value%']/following-sibling::div/textarea";
    String ListBox = "//label[text()='%value%']/following-sibling::div/descendant::button";
    String ListMenu = "//lightning-base-combobox-item//span[text()='%value%']";
    String SupportWorkType = "//label[text()='Support Work Type']/following-sibling::div/descendant::button/span[text()='%value%']";
    SelenideElement Save = $(By.xpath("//button[text()='Save']"));
    SelenideElement ErrorIcon = $(By.xpath("//lightning-icon[@title='Error']/parent::button"));
    ElementsCollection ErrorLinks = $$(By.xpath("//records-record-edit-error//ul/li"));
    ElementsCollection MandatoryFields = $$(By.xpath("//records-record-edit-error//ul/li/a"));
}
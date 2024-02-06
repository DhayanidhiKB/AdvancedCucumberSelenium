package com.my.salesforce.sandbox.application.elements.contract.dov;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface NewDoVLanguageElements {
    SelenideElement Header = $(By.xpath("//h2[text()='New DoV Language']"));
    String Dov = "//label[text()='%value%']/following-sibling::div/lightning-base-combobox";
    String Option = "//span[@title='%value%']";
    SelenideElement Save = $(By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.APT_DoV_Language__c.SaveEdit']"));
    SelenideElement SaveAndEdit = $(By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.APT_DoV_Language__c.SaveAndNew']"));
    SelenideElement Cancel = $(By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.APT_DoV_Language__c.CancelEdit']"));
}
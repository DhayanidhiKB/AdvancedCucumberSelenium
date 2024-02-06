package com.my.salesforce.sandbox.application.elements.contract.dov;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface DOVLanguagesElements {
    SelenideElement Header = $(By.xpath("//h1[@title='DoV Languages']"));
    SelenideElement NewLanguage = $(By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.APT_DoV_Language__c.New']"));
    SelenideElement Contract = $(By.xpath("//a[@title='Apttus Contracts']/parent::li/following-sibling::li/a"));
    SelenideElement SelectAll = $(By.xpath("//input[@class='datatable-select-all']/parent::span"));
    SelenideElement DovLanguage = $(By.xpath("//span[contains(text(),'DoV-L-')]/ancestor::a"));
}
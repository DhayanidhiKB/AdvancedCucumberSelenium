package com.my.salesforce.sandbox.application.elements.organisations.new_organisation;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface NewOrganisationElements {
    SelenideElement Header = $(By.xpath("//h2[text()='New Organisation: Organisation']"));
    SelenideElement Save = $(By.xpath("//button[text()='Save']"));
    SelenideElement LegalEntityNameError = $(By.xpath("//a[text()='Legal Entity Name']/ancestor::records-record-edit-error"));
    SelenideElement ErrorIcon = $(By.xpath("//lightning-icon[@title='Error']/parent::button"));
}
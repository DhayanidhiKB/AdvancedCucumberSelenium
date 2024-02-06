package com.my.salesforce.sandbox.application.elements.organisations.contacts;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface NewContactElements {
    SelenideElement Heading = $(By.xpath("//h2[text()='New Contact: Contact']"));
    SelenideElement Title = $(By.xpath("//label[text()='Title']/following-sibling::div/descendant::button"));
    String Option = "//span[@title='%value%']";
    String Enter = "//label[text()='%value%']/following-sibling::div/descendant::input";
    SelenideElement Save = $(By.xpath("//button[text()='Save']"));
    ElementsCollection WarningButton = $$(By.xpath("//button[@id='window']"));
    SelenideElement NameError = $(By.xpath("//a[text()='Name']/ancestor::records-record-edit-error"));
    SelenideElement ErrorIcon = $(By.xpath("//lightning-icon[@title='Error']/parent::button"));
}
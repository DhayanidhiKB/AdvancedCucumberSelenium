package com.my.salesforce.sandbox.application.elements.opportunities.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface ActivityElements {
    SelenideElement NewTask = $(By.xpath("//button[@title='New Task']"));
    SelenideElement Subject = $(By.xpath("//label[text()='Subject']/following-sibling::div//input"));
    String Option = "//span[text()='%value%']/ancestor::lightning-base-combobox-item";
    SelenideElement SearchContacts = $(By.xpath("//input[@title='Search Contacts']"));
    SelenideElement Save = $(By.xpath("//div[contains(@class,'bottomBarRight')]/descendant::button/span[text()='Save']"));
}
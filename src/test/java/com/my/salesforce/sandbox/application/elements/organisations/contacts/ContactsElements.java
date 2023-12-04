package com.my.salesforce.sandbox.application.elements.organisations.contacts;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface ContactsElements {
    SelenideElement Contacts = $(By.xpath("//span[@title='Contacts']/parent::a"));
    SelenideElement NewContact = $(By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.Contact.NewContact']"));
    SelenideElement MergeContacts = $(By.xpath("//li[@data-target-selection-name='sfdc:CustomButton.Contact.Merge_Contacts']"));
    String ContactLink = "//span[text()='%value%']/ancestor::a";
    String Email = "//dt[text()='Email:']/following-sibling::dd//a[text()='%value%']";
}
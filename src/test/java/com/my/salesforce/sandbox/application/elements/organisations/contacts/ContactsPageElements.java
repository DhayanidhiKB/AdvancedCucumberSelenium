package com.my.salesforce.sandbox.application.elements.organisations.contacts;

import org.openqa.selenium.By;

public interface ContactsPageElements {
    By Heading = By.xpath("//h1[text()='Contacts']");
    By New = By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.Contact.NewContact']");
}
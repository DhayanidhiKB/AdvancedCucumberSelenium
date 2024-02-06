package com.my.salesforce.sandbox.application.elements.organisations.contacts;

import org.openqa.selenium.By;

public interface NewContactPageElements {
    By Heading = By.xpath("//h2[text()='New Contact: Contact']");
    By Title = By.xpath("//label[text()='Title']/following-sibling::div/descendant::button");
    String Option = "//span[@title='%value%']";
    String Enter = "//label[text()='%value%']/following-sibling::div/descendant::input";
    By Save = By.xpath("//button[text()='Save']");
    By WarningButton = By.xpath("//button[@id='window']");
}
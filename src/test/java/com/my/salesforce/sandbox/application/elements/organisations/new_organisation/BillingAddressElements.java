package com.my.salesforce.sandbox.application.elements.organisations.new_organisation;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface BillingAddressElements {
    SelenideElement BillingStreet = $(By.xpath("//label[text()='Billing Street']/following-sibling::div//textarea"));
    SelenideElement BillingCity = $(By.xpath("//label[text()='Billing City']/following-sibling::div//input"));
    SelenideElement BillingState = $(By.xpath("//label[text()='Billing State']/following-sibling::div//input"));
    SelenideElement BillingPostalCode = $(By.xpath("//label[text()='Billing Postal Code']/following-sibling::div//input"));
    SelenideElement BillingCountry = $(By.xpath("//label[text()='Billing Country']/following-sibling::div//input"));
}
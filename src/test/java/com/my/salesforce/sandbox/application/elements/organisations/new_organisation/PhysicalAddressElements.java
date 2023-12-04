package com.my.salesforce.sandbox.application.elements.organisations.new_organisation;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface PhysicalAddressElements {
    SelenideElement PhysicalStreet = $(By.xpath("//label[text()='Physical Street']/following-sibling::div//textarea"));
    SelenideElement PhysicalCity = $(By.xpath("//label[text()='Physical City']/following-sibling::div//input"));
    SelenideElement PhysicalState = $(By.xpath("//label[text()='Physical State']/following-sibling::div//input"));
    SelenideElement PhysicalPostalCode = $(By.xpath("//label[text()='Physical Postal Code']/following-sibling::div//input"));
    SelenideElement PhysicalCountry = $(By.xpath("//label[text()='Physical Country']/following-sibling::div//input"));
}
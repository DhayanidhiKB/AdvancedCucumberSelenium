package com.my.salesforce.sandbox.application.elements.contract.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface SAPContractsPageElements {
    SelenideElement Header = $(By.xpath("//h1[text()='SAP Contracts']"));
    SelenideElement SapContract = $(By.xpath("//a[contains(text(),'SAP-SFNo')]"));
    String Edit = "//button[@title='Edit %value%']";
}
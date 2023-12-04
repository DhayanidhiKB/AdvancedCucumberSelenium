package com.my.salesforce.sandbox.application.elements.opportunities.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface OpportunityTabSetElements {
    SelenideElement CustomerNeeds = $(By.xpath("(//a[text()='Customer Needs'])[1]"));
    SelenideElement ProductsContracts = $(By.xpath("(//a[text()='Products & Contracts'])[1]"));
    String Link = "(//a[text()='%value%'])[1]";
    SelenideElement More = $(By.xpath("(//button[@title='More Tabs'])[1]"));
    String SubLink = "//span[text()='%value%']/parent::a";
}
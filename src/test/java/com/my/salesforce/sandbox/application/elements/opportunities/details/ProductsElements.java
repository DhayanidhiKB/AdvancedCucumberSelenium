package com.my.salesforce.sandbox.application.elements.opportunities.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface ProductsElements {
    SelenideElement Header = $(By.xpath("//h1[text()='Products (Standard Price Book)']"));
    SelenideElement SelectAll = $(By.xpath("//span[text()='Select All']/parent::label"));
    SelenideElement ProductsLink = $(By.xpath("//a[@part='breadcrumb' and text()='Products']/parent::lightning-breadcrumb"));
    SelenideElement OpportunitiesLink = $(By.xpath("//a[@part='breadcrumb' and text()='Opportunities']/parent::lightning-breadcrumb"));
    String Opportunity = "//a[@part='breadcrumb' and text()='%value%']/parent::lightning-breadcrumb";
    SelenideElement EditQuantity = $(By.xpath("//span[text()='Edit Quantity']/ancestor::button"));
    SelenideElement EditUnitSales = $(By.xpath("//span[text()='Edit Unit Sales Price (Ex GST)']/ancestor::button"));
    SelenideElement Save = $(By.xpath("//button[text()='Save']"));
    SelenideElement Cancel = $(By.xpath("//button[text()='Cancel']"));
}
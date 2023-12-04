package com.my.salesforce.sandbox.application.elements.opportunities.details.edit_products;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface EditProductsElements {
    SelenideElement Header = $(By.xpath("//div[text()='Edit Products']"));
    SelenideElement SelectAll = $(By.xpath("//span[text()='Select All']/parent::label"));
    SelenideElement ProductsLink = $(By.xpath("//a[@part='breadcrumb' and text()='Products']/parent::lightning-breadcrumb"));
    SelenideElement OpportunitiesLink = $(By.xpath("//a[@part='breadcrumb' and text()='Opportunities']/parent::lightning-breadcrumb"));
    String Opportunity = "//a[@part='breadcrumb' and text()='%value%']/parent::lightning-breadcrumb";
    SelenideElement EditQuantity = $(By.xpath("//span[text()='Edit Quantity']/ancestor::button"));
    SelenideElement QuantityIcon = $(By.xpath("//span[text()='Quantity']/preceding-sibling::lightning-icon"));
    SelenideElement EditUnitSales = $(By.xpath("//span[text()='Edit Unit Sales Price (Ex GST)']/ancestor::button"));
    SelenideElement Error = $(By.xpath("//span[text()='Edit Quantity has error']"));
    SelenideElement EditStartDate = $(By.xpath("//span[text()='Edit Revenue Start Date']/ancestor::button"));
    SelenideElement StartDate = $(By.xpath("//label[text()='Revenue Start Date']/following-sibling::div/input"));
    SelenideElement EditEndDate = $(By.xpath("//span[text()='Edit Revenue End Date']/ancestor::button"));
    SelenideElement EndDate = $(By.xpath("//label[text()='Revenue Start Date']/following-sibling::div/input"));
    SelenideElement Save = $(By.xpath("//button[text()='Save']"));
    SelenideElement Cancel = $(By.xpath("//button[text()='Cancel']"));

    SelenideElement ProductCheckBox=$(By.xpath("(//span[@class='slds-checkbox_faux'])[1]"));
    SelenideElement ConfirmOpportunityProducts = $(By.xpath("//button[text()='Confirm Opportunity Products']"));
}
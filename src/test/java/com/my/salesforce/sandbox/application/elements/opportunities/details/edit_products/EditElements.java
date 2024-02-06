package com.my.salesforce.sandbox.application.elements.opportunities.details.edit_products;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface EditElements {
    SelenideElement QuantityIcon = $(By.xpath("//lightning-icon[@title='Quantity']"));
    SelenideElement StartDateIcon = $(By.xpath("//lightning-icon[@title='Revenue Start Date']"));
    SelenideElement EndDateIcon = $(By.xpath("//lightning-icon[@title='Revenue End Date']"));
    SelenideElement UnitSalesIcon = $(By.xpath("//lightning-icon[@title='Unit Sales Price (Ex GST)']"));
    ElementsCollection EditQuantity = $$(By.xpath("//span[text()='Edit Quantity']/ancestor::button"));
    ElementsCollection EditRevenueStartDate = $$(By.xpath("//span[text()='Edit Revenue Start Date']/ancestor::button"));
    ElementsCollection EditRevenueEndDate = $$(By.xpath("//span[text()='Edit Revenue End Date']/ancestor::button"));
    ElementsCollection EditUnitSales = $$(By.xpath("//span[text()='Edit Unit Sales Price (Ex GST)']/ancestor::button"));
    SelenideElement InputQuantity = $(By.xpath("//label[text()='Quantity']/following-sibling::div/input"));
    SelenideElement InputStartDate = $(By.xpath("//label[text()='Revenue Start Date']/following-sibling::div/input"));
    SelenideElement InputEndDate = $(By.xpath("//label[text()='Revenue End Date']/following-sibling::div/input"));
    SelenideElement InputUnitSales = $(By.xpath("//label[text()='Unit Sales Price (Ex GST)']/following-sibling::div/input"));
    SelenideElement QuantityError = $(By.xpath("//span[text()='Edit Quantity has error']"));
    SelenideElement StartDateError = $(By.xpath("//span[text()='Edit Start Date has error']"));
    SelenideElement EndDateError = $(By.xpath("//span[text()='Edit End Date has error']"));
    SelenideElement Save = $(By.xpath("//button[text()='Save']"));
    SelenideElement Cancel = $(By.xpath("//button[text()='Cancel']"));
}
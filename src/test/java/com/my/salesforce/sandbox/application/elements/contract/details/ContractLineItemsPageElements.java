package com.my.salesforce.sandbox.application.elements.contract.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface ContractLineItemsPageElements {
    SelenideElement Header = $(By.xpath("//h1[@title='Contract Line Items']"));
    String Controls = "//button[@title='%value%']";
    SelenideElement ContractItem = $(By.xpath("(//a[contains(@title,'VS-')])[1]"));
}
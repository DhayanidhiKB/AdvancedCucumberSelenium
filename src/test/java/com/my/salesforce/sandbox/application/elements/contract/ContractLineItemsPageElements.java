package com.my.salesforce.sandbox.application.elements.contract;

import org.openqa.selenium.By;

public interface ContractLineItemsPageElements {
    By Header = By.xpath("//h1[@title='Contract Line Items']");
    String Controls = "//button[@title='%value%']";
    By ContractItem = By.xpath("(//a[contains(@title,'VS-')])[1]");
}
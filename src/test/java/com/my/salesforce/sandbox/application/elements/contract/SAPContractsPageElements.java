package com.my.salesforce.sandbox.application.elements.contract;

import org.openqa.selenium.By;

public interface SAPContractsPageElements {
    By Header = By.xpath("//h1[text()='SAP Contracts']");
    By SapContract = By.xpath("//a[contains(text(),'SAP-SFNo')]");
    String Edit = "//button[@title='Edit %value%']";
    By ClosePage = By.xpath("//lightning-icon[@icon-name='utility:close']/parent::button");
}
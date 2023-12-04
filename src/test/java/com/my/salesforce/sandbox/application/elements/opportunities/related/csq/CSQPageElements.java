package com.my.salesforce.sandbox.application.elements.opportunities.related.csq;

import org.openqa.selenium.By;

public interface CSQPageElements {
    By Header = By.xpath("//h1[@title='CSQ']");
    By NewOption = By.xpath("//h1[@title='CSQ']/ancestor::div[contains(@class,'firstHeaderRow')]/following-sibling::div//a[@title='New']");
    By GetCSQName = By.xpath("//span[text()='Draft']/ancestor::td/preceding-sibling::th//a");
    String CsqLink = "//a[@title='%value%']";
}
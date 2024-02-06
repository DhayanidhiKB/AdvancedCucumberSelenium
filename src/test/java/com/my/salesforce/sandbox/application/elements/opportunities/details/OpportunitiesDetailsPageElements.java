package com.my.salesforce.sandbox.application.elements.opportunities.details;

import org.openqa.selenium.By;

public interface OpportunitiesDetailsPageElements {
    String Tab = "(//a[text()='%value1%'])[%value2%]";
    By MoreTabs = By.xpath("//li[@title='Products & Contracts']/following-sibling::li//button[@title='More Tabs']");
    String MoreTabsOption = "//span[text()='%value%']/parent::a";
    By EditKeyFields = By.xpath("//a[@title='Edit Key Fields']");
    String Edit = "(//button[@title='Edit %value%'])[1]";
    By StageIdentify = By.xpath("//button[@data-value='Identify']/ancestor::lightning-base-combobox");
    String ListMenu = "//span[@title='%value%']";
    String Action = "//button[text()='%value%']";
}
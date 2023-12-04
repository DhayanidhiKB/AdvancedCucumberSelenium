package com.my.salesforce.sandbox.application.elements.opportunities.related.csq;

import org.openqa.selenium.By;

public interface NewFreightOfferingElements {
    By Header = By.xpath("//h2[text()='New Freight Offering']");
    String RecordType = "//span[text()='%value%']/parent::div/preceding-sibling::div//span";
    String Button = "//div[@class='forceChangeRecordTypeFooter']/button/span[text()='%value%']";
    By Cartons = By.xpath("//input[@name='Cartons__c']");
    String DualListBox = "//div[text()='%value1%']/following-sibling::div/descendant::ul/li//span[@title='%value2%']";
    String Arrow = "(//div[text()='%value1%']/following-sibling::div/descendant::button[@title='Move selection to %value2%'])[1]";
    String Input = "(//label[text()='%value1%']/following-sibling::div/input)[%value2%]";
    String ListBox = "//label[text()='%value%']/following-sibling::div//button";
    String Option = "//span[text()='%value%']";
    String ComboBox = "//label[text()='%value%']/following-sibling::div//input";
    By Save = By.xpath("//button[text()='Save']");
}
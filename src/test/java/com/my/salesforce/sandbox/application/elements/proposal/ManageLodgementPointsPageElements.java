package com.my.salesforce.sandbox.application.elements.proposal;

import org.openqa.selenium.By;

public interface ManageLodgementPointsPageElements {
    String Input = "//input[@value='%value%']";
    By MainTitle = By.xpath("//h2[text()='Add Product Lodgement Point']");
    By PostCode = By.xpath("//b[contains(text(),'Post Code')]/following-sibling::div//input");
    String Lp = "//span[text()='%value%']/parent::td/preceding-sibling::td/input";
    By SelectedLP = By.xpath("//span[contains(@id,'selectedLP')]");
}
package com.my.salesforce.sandbox.application.elements.contract;

import org.openqa.selenium.By;

public interface ManageLodgementPointsAndAccountNumbersPageElements {
    By iFrame = By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]");
    By MainTitle = By.xpath("//h2[text()='Manage Lodgement Points and Account Numbers']");
    String Button = "//input[@value='%value%']";
    By ProductTable = By.xpath("//table[@class='detailList']");
    By ProductsList = By.xpath("//table[@class='detailList']//tbody[contains(@id,'aLITable')]/tr");
    By Product = By.xpath("//table[@class='detailList']//tbody[contains(@id,'aLITable')]//input");
    String Choose = "//span[text()='%value%']/parent::td/preceding-sibling::td/input";
    String Input = "//b[text()='%value%']/following-sibling::div/input";
    String Lp = "//span[text()='%value%']/parent::td/preceding-sibling::td/input";
    By ManageAccountNumbers = By.xpath("//b[text()='Manage Account Number']/parent::a");
}
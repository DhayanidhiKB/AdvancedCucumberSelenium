package com.my.salesforce.sandbox.application.elements.proposal;

import org.openqa.selenium.By;

public interface RateCardKeyElements {
    By iFrame = By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]");
    By Header = By.xpath("//div[text()='Import rates from P&Y Tool']");
    By RateCardKey = By.xpath("//input[contains(@name,'ratecardKey')]");
    String Button = "//input[@value='%value%']";
    By Warning = By.xpath("//label[text()='Once the RK Code is applied, you should not edit this product.']");
    By Confirm = By.xpath("//button[text()='Confirm & Continue']");
    By ConfirmationPopUp = By.xpath("//div[text()='Confirmation Required']");
    String Confirmation = "//button[text()='%value%']";
}
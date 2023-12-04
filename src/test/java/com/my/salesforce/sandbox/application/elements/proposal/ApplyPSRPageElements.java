package com.my.salesforce.sandbox.application.elements.proposal;

import org.openqa.selenium.By;

public interface ApplyPSRPageElements {
    By FirstHeader = By.xpath("//span[text()='List of Work In Progress PSRs']/parent::h1");
    By SecondHeader = By.xpath("//span[text()='List of Completed PSRs']/parent::h1");
    By BackToCart = By.xpath("//button[text()='Back to Shopping Cart']");
    By ApplyPSR = By.xpath("//button[text()='Apply PSR']");
    By iFrame = By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]");
    By QuoteID = By.xpath("//th[@data-label='COMPASS Quote ID']//lightning-base-formatted-text");
    By SuccessMessage = By.xpath("//h2[text()='Rates available for check out']");
}
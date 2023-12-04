package com.my.salesforce.sandbox.application.elements.shopping_cart.appc;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface ApplyPSRPageElements {
    SelenideElement FirstHeader = $(By.xpath("//span[text()='List of Work In Progress PSRs']/parent::h1"));
    SelenideElement SecondHeader = $(By.xpath("//span[text()='List of Completed PSRs']/parent::h1"));
    SelenideElement BackToCart = $(By.xpath("//button[text()='Back to Shopping Cart']"));
    SelenideElement ApplyPSR = $(By.xpath("//button[text()='Apply PSR']"));
    SelenideElement iFrame = $(By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]"));
    SelenideElement QuoteID = $(By.xpath("//th[@data-label='COMPASS Quote ID']//lightning-base-formatted-text"));
    SelenideElement SuccessMessage = $(By.xpath("//h2[text()='Rates available for check out']"));
}
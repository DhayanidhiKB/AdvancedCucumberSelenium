package com.my.salesforce.sandbox.application.elements.shopping_cart.eparcel;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface RateCardKeyElements {
    SelenideElement iFrame = $(By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]"));
    SelenideElement Header = $(By.xpath("//div[text()='Import rates from P&Y Tool']"));
    SelenideElement RateCardKey = $(By.xpath("//input[contains(@name,'ratecardKey')]"));
    String Button = "//input[@value='%value%']";
    SelenideElement Warning = $(By.xpath("//label[text()='Once the RK Code is applied, you should not edit this product.']"));
    SelenideElement Confirm = $(By.xpath("//button[text()='Confirm & Continue']"));
    SelenideElement ConfirmationPopUp = $(By.xpath("//div[text()='Confirmation Required']"));
    String Confirmation = "//button[text()='%value%']";
}
package com.my.salesforce.sandbox.application.elements.contract.renew;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface RenewContractElements {
    SelenideElement iFrame = $(By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]"));
    SelenideElement Header = $(By.xpath("//h2[text()='Renew Contract']"));
    String DropDown = "//label[contains(text(),'%value%')]/ancestor::tr/following-sibling::tr//select";
    String Input = "//input[@value='%value%']";
    SelenideElement ExistingOpportunity = $(By.xpath("//label[contains(text(),'existing opportunity?')]/ancestor::tr/following-sibling::tr//input"));
}
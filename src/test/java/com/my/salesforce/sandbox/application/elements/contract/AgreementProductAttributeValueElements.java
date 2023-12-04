package com.my.salesforce.sandbox.application.elements.contract;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface AgreementProductAttributeValueElements {
    SelenideElement Header = $(By.xpath("//div[text()='Agreement Product Attribute Value']/parent::h1"));
    String GetText = "//span[text()='%value%']/parent::div/following-sibling::div/descendant::lightning-formatted-text";
    String Label = "//span[text()='%value%']";
}
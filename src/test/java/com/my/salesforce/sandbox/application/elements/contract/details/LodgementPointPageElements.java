package com.my.salesforce.sandbox.application.elements.contract.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface LodgementPointPageElements {
    SelenideElement Header = $(By.xpath("//div[text()='Agreement Lodgement Point']/parent::h1"));
    String Edit = "//button[@title='Edit %value%']";
    String Text = "//span[text()='%value1%']/parent::div/following-sibling::div//lightning-formatted-text[text()='%value2%']";
    String NavigationBar = "//span[text()='%value%']/ancestor::one-app-nav-bar-item-root";
}
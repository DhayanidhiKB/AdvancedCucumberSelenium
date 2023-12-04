package com.my.salesforce.sandbox.application.elements.contract;

import org.openqa.selenium.By;

public interface LodgementPointPageElements {
    By Header = By.xpath("//div[text()='Agreement Lodgement Point']/parent::h1");
    String Edit = "//button[@title='Edit %value%']";
    String Text = "//span[text()='%value1%']/parent::div/following-sibling::div//lightning-formatted-text[text()='%value2%']";
    String GetText = "//span[text()='%value%']/parent::div/following-sibling::div//lightning-formatted-text";
    String NavigationBar = "//span[text()='%value%']/ancestor::one-app-nav-bar-item-root";
}
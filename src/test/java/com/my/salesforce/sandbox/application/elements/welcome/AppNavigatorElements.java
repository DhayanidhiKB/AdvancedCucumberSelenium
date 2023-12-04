package com.my.salesforce.sandbox.application.elements.welcome;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public interface AppNavigatorElements {
    SelenideElement AppNavigationContainer = $(".slds-no-print.oneAppNavContainer");
    SelenideElement OneAppLauncher = $(By.xpath("//span[normalize-space()='App Launcher']/ancestor::one-app-launcher-header"));
    SelenideElement SearchApps = $(By.xpath("//input[@placeholder='Search apps and items...']"));
    String App = "//b[normalize-space()='%value%']";
    String AppName = "//span[contains(@class,'appName')]/span[text()='%value%']";
    SelenideElement Organisations = $(By.xpath("//a[@title='Organisations']/parent::one-app-nav-bar-item-root"));
    SelenideElement Opportunities = $(By.xpath("//a[@title='Opportunities']/parent::one-app-nav-bar-item-root"));
}
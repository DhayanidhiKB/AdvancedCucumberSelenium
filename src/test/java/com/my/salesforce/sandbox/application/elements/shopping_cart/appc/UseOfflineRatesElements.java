package com.my.salesforce.sandbox.application.elements.shopping_cart.appc;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface UseOfflineRatesElements {
    SelenideElement Header = $(By.xpath("//h1[text()='Use Offline Rates Checkout']"));
    SelenideElement Reason = $(By.xpath("//label[text()='Reason for Offline checkout']/following-sibling::div//button"));
    SelenideElement Comments = $(By.xpath("//label[text()='Comments']/following-sibling::div//textarea"));
    SelenideElement Next = $(By.xpath("//button[text()='Next']"));
    SelenideElement Cancel = $(By.xpath("//button[text()='Cancel']"));
}
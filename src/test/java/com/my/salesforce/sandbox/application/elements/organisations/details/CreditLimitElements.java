package com.my.salesforce.sandbox.application.elements.organisations.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface CreditLimitElements {
    SelenideElement EditSTCreditLimit = $(By.xpath("//button[@title='Edit StarTrack Approved Credit Limit']"));
    SelenideElement EnterSTCreditLimit = $(By.xpath("//input[@name='L2C_Star_Track_Approved_Credit_Limit__c']"));
    SelenideElement Save = $(By.xpath("//runtime_platform_actions-action-renderer[@title=\"Save\"]/parent::li"));
    SelenideElement STCreditLimit = $(By.xpath("//span[text()='StarTrack Approved Credit Limit']/parent::div/following-sibling::div//lightning-formatted-text"));
}
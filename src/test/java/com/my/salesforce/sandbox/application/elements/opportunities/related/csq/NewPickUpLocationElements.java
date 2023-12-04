package com.my.salesforce.sandbox.application.elements.opportunities.related.csq;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface NewPickUpLocationElements {
    SelenideElement Header = $(By.xpath("//span[text()='New Pick-Up Location']/parent::h2"));
    String Search = "//input[@placeholder='Search %value%']";
    SelenideElement OverrideAddress = $(By.xpath("//span[text()='Override Address']/preceding-sibling::span"));
    String Input = "//label[text()='%value%']/ancestor::lightning-input//input";
    SelenideElement Save = $(By.xpath("//button[text()='Save']"));
    SelenideElement ApprovalStatus = $(By.xpath("//button[@name='Pick_up_Location_Status__c']"));
    SelenideElement LocationType = $(By.xpath("//button[@name='Location_Type__c']"));
    String Option = "//span[text()='%value%']";
}
package com.my.salesforce.sandbox.application.elements.opportunities.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface MarkStageElements {
    SelenideElement Header = $(By.xpath("//h2[text()='Edit Dependencies']"));
    SelenideElement Stage = $(By.xpath("//button[@name='StageName']/parent::div"));
    SelenideElement NextStep = $(By.xpath("//button[@name='Next_Step__c']/parent::div"));
    String Option = "//lightning-base-combobox-item[@data-value='%value%']";
    SelenideElement Cancel = $(By.xpath("//button[text()='Cancel']"));
    SelenideElement Done = $(By.xpath("//button[text()='Done']"));
}
package com.my.salesforce.sandbox.application.elements.opportunities.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface OpportunityDetailsElements {
    String Edit = "(//button[@title='Edit %value%'])[1]";
    SelenideElement Stage = $(By.xpath("//label[text()='Stage']/following-sibling::div"));
    SelenideElement NextStep = $(By.xpath("//label[text()='Next Step']/following-sibling::div"));
    String Action = "//button[text()='%value%']";
    SelenideElement RevenueHeader = $(By.xpath("//span[text()='Revenue']/ancestor::h3"));
    String TextArea = "//label[text()='%value%']/following-sibling::div/textarea";
}
package com.my.salesforce.sandbox.application.elements.opportunities.details.sub_header;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public interface OpportunitySubHeaderElements {
    String CurrentStage = "//span[@class='current slds-path__stage']/ancestor::li[@data-name='%value%']";
    String AheadStage = "//span[@class='ahead slds-path__stage']/ancestor::li[@data-name='%value%']";
    SelenideElement OutstandingActions = $(By.xpath("//h2/div[text()='Outstanding Actions']"));
    SelenideElement Refresh = $(By.xpath("//button[@title='Refresh']/ancestor::div[@class='slds-no-flex']"));
    SelenideElement Activity = $(By.xpath("//a[text()='Activity']/parent::li"));
}
package com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface NewOpportunityPageElements {
    SelenideElement Header = $(By.xpath("//h2[text()='New Opportunity: Simple']"));
    SelenideElement Save = $(By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.Opportunity.SaveEdit']"));
    SelenideElement ErrorIcon = $(By.xpath("//lightning-icon[@title='Error']/parent::button"));
    ElementsCollection ErrorLinks = $$(By.xpath("//records-record-edit-error//ul/li"));
    ElementsCollection MandatoryFields = $$(By.xpath("//records-record-edit-error//ul/li/a"));
}
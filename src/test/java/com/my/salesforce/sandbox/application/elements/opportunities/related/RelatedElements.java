package com.my.salesforce.sandbox.application.elements.opportunities.related;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface RelatedElements {
    SelenideElement Header = $(By.xpath("//span[@title='qualifications']/parent::div"));
    SelenideElement NewCompetitor = $(By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.Competitor__c.New']"));
    SelenideElement NewCompetitorTable = $(By.xpath("//table[@aria-label='Competitors and Incumbents']"));
    SelenideElement NewCSQ = $(By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.Customer_Scoping_Questionnaire__c.New']"));
    SelenideElement CSQLink = $(By.xpath("//span[contains(text(),'CSQ-')]/ancestor::records-hoverable-link"));
}
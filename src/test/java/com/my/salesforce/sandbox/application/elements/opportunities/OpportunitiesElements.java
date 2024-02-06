package com.my.salesforce.sandbox.application.elements.opportunities;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface OpportunitiesElements {
    ElementsCollection Heading = $$(By.cssSelector(".slds-page-header__name-title > h2 > span"));
    SelenideElement New = $(By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.Opportunity.New']"));
    SelenideElement SearchTheList = $(By.xpath("//input[@name='Opportunity-search-input']"));
}
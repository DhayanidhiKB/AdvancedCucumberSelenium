package com.my.salesforce.sandbox.application.elements.opportunities.related.csq;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface CSQQuestionnairePageElements {
    SelenideElement Header = $(By.xpath("//div[text()='Customer Scoping Questionnaire (CSQ)']"));
    String Links = "//span[@title='%value%']";
    SelenideElement NewFreightOfferings = $(By.xpath("//span[@title='Freight Offerings']/ancestor::div[contains(@class,'firstHeaderRow')]//following-sibling::div//button[@name='New']"));
    SelenideElement FreightOfferingName = $(By.xpath("//lightning-primitive-cell-factory[@data-label='Freight Offering Name']//a//span"));
    String Action = "//runtime_platform_actions-action-renderer[@title='%value%']/parent::li";
}
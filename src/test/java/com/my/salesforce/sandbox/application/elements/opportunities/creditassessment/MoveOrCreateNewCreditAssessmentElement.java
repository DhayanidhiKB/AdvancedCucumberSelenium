package com.my.salesforce.sandbox.application.elements.opportunities.creditassessment;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface MoveOrCreateNewCreditAssessmentElement {
    SelenideElement Header = $(By.xpath("//p[text()='To complete credit assessment process, select below approved credit assessment or initiate a new credit assessment:']/parent::div"));
    SelenideElement Table = $(By.xpath("//lightning-datatable//table"));
    SelenideElement RadioOption = $(By.xpath("//span[text()='Select Item 1']/ancestor::label"));
    String Option = "//button[text()='%value%']/parent::lightning-button";
    SelenideElement CancelSelection = $(By.xpath("//button[text()='Cancel']/parent::lightning-button"));
    SelenideElement NewCreditAssessmentMessage = $(By.xpath("//p[contains(text(),'By clicking \"OK\" a new credit assessment will be initiated.')]"));
    SelenideElement MoveMessage1 = $(By.xpath("//p[contains(text(),'By clicking \"OK\" Credit Assess - ')]"));
    SelenideElement MoveMessage2 = $(By.xpath("//p[contains(text(),'will be linked to Proposal Q-')]"));
    SelenideElement Ok = $(By.xpath("//div/child::button[text()='OK']"));
    SelenideElement Cancel = $(By.xpath("//div/child::button[text()='Cancel']"));
}
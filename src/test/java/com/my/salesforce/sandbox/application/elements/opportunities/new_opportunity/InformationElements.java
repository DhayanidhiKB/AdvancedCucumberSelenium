package com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface InformationElements {
    SelenideElement OpportunitySubType = $(By.xpath("//label[text()='Opportunity SubType']/following-sibling::div/child::lightning-base-combobox"));
    SelenideElement Stage = $(By.xpath("//label[text()='Stage']/following-sibling::div/child::lightning-base-combobox"));
    SelenideElement NextStep = $(By.xpath("//label[text()='Next Step']/following-sibling::div/child::lightning-base-combobox"));

    String Option = "//span[@title='%value%']/ancestor::lightning-base-combobox-item";
    SelenideElement OpportunityName = $(By.xpath("//label[text()='Opportunity Name']/following-sibling::div/input"));
    SelenideElement LegalEntityName = $(By.xpath("//label[text()='Legal Entity Name']/following-sibling::div//input"));
    SelenideElement KeyContact = $(By.xpath("//label[text()='Key Contact']/following-sibling::div//input"));
    String SearchAllResultsFor = "//span[contains(@title,'Show All Results for') and contains(text(),'%value%')]";
    SelenideElement CloseDate = $(By.xpath("//label[text()='Close Date']/following-sibling::div/input"));
    SelenideElement ClosedComments = $(By.xpath("//label[text()='Closed Comments']/following-sibling::div/textarea"));
    SelenideElement IsItStarTrack = $(By.xpath("//label[text()='StarTrack Opportunity?']/following-sibling::div/child::lightning-base-combobox"));
    SelenideElement PostcodeMapping = $(By.xpath("//input[@placeholder='Search Post Code Mapping...']"));
    SelenideElement MinimumMonthlySpend = $(By.xpath("//label[text()='StarTrack Minimum Monthly Spend']/following-sibling::div/input"));
}
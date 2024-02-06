package com.my.salesforce.sandbox.application.elements.opportunities.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface CloseOpportunityElements {
    SelenideElement Header = $(By.xpath("//h2[text()='Close Opportunity']"));
    SelenideElement CancelClosure = $(By.xpath("//button[text()='Cancel Closure']"));
    SelenideElement ConfirmOPC = $(By.xpath("//button[text()='Confirm Opportunity Products']"));
    SelenideElement StageName = $(By.xpath("//button[@name='StageName']/parent::div"));
    SelenideElement NextSteps = $(By.xpath("//button[@name='NextSteps']/parent::div"));
    SelenideElement ClosedReason = $(By.xpath("//button[@name='Closed Reason']/parent::div"));
    String Option = "//span[@title='%value%']/ancestor::lightning-base-combobox-item";
    SelenideElement ClosedComments = $(By.xpath("//label[text()='Closed Comments']/following-sibling::div/input"));
    SelenideElement CloseOpportunity = $(By.xpath("//button[text()='Close Opportunity']"));

    SelenideElement DataIntegrity = $(By.xpath("//b[text()='Data Integrity Compliance Acknowledgment']/parent::span"));
    SelenideElement Agree = $(By.xpath("//span[text()='Agree']/preceding-sibling::span"));
    SelenideElement OpenDSRs = $(By.xpath("//b[text()='Open Deal Support Requests']"));
    SelenideElement Next = $(By.xpath("//button[text()='Next']/parent::lightning-button"));
    SelenideElement OpportunityClosed = $(By.xpath("//span[text()='Opportunity has been closed. Please Close this window to go back to the Opportunity Record.']/parent::div"));
    SelenideElement Close = $(By.xpath("//c-opportunity-closure//button[text()='Close']"));
}
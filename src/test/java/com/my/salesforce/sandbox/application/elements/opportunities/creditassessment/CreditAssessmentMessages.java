package com.my.salesforce.sandbox.application.elements.opportunities.creditassessment;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface CreditAssessmentMessages {
    SelenideElement CompleteAndApprove = $(By.xpath("//p[text()='Prospect customer requires credit assessment to be completed and approved prior generating contract document. Click \"OK\" to submit credit assessment.']/parent::lightning-alert"));
    SelenideElement OK = $(By.xpath("//button[text()='OK']"));
    SelenideElement OPCRequired = $(By.xpath("//span[text()='Opportunity Classification (OPC) against Primary Proposal is required before conducting a Credit Assessment.']"));
    SelenideElement AfterOPCHitReload = $(By.xpath("//span[text()='Once OPC is completed, hit the Reload button to refresh this tab.']"));
}
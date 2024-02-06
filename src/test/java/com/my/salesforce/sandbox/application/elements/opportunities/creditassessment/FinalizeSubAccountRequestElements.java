package com.my.salesforce.sandbox.application.elements.opportunities.creditassessment;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface FinalizeSubAccountRequestElements {
    SelenideElement Header = $(By.xpath("//h2[text()='Finalize Sub Account Request']"));
    SelenideElement Message1 = $(By.xpath("//b[contains(text(),'sub-account requests? You cannot modify or delete the request once finalized.')]"));
    SelenideElement Message2 = $(By.xpath("//b[contains(text(),'Sub-Account Request Finalized. Sub-account will be created when charge account request is submitted.')]"));
    SelenideElement Cancel = $(By.xpath("//footer/button[text()='Cancel']"));
    SelenideElement FinalizeRequest = $(By.xpath("//footer/button[text()='Finalize Request(s)']"));
    SelenideElement Close = $(By.xpath("//footer/button[text()='Close']"));
}
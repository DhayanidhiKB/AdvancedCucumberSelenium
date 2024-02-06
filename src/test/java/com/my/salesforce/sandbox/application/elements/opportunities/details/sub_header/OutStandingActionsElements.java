package com.my.salesforce.sandbox.application.elements.opportunities.details.sub_header;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface OutStandingActionsElements {
    SelenideElement ThemeError = $(By.xpath("//div[contains(@class,'slds-theme_error')]"));
    SelenideElement ThemeSuccess = $(By.xpath("//div[contains(@class,'slds-text-color_success') and contains(text(),'You can progress from')]"));
    SelenideElement MessageIdentifyToQualify = $(By.xpath("//span[text()='Add a Description. A summary is required so that others can understand the proposed opportunity and the customer’s requirements.']"));
    SelenideElement MessageQualifyToPropose1 = $(By.xpath("//span[text()='Add Competitors and Incumbents details. Populate the customer’s incumbent or known competitors on the Related tab.']"));
    SelenideElement MessageQualifyToPropose2 = $(By.xpath("//span[text()='Add your Status Update. The Status Update must be populated with a summary of the current position of the Opportunity.']"));
    SelenideElement MessageQualifyToPropose3 = $(By.xpath("//span[contains(text(),'Add a product. This opportunity has no products. Please add a product(s) by going to the product catalogue')]"));
    SelenideElement MessageProposeToNegotiate = $(By.xpath("//span[text()='Add an Activity. Sales tasks and events must be added to provide visibility of your customer interactions and details of your discussions with the customer.']"));
    SelenideElement MessageNegotiateToClosedWon1 = $(By.xpath("//span[text()='Complete the contract. The fully signed digital contract must be received through DocuSign. For offline contracts, request Customer Onboarding acknowledge receipt of the offline contract via a DSR.']"));
    SelenideElement MessageNegotiateToClosedWon2 = $(By.xpath("//span[text()='This opportunity is currently associated with a Prospect or a Sales Contact. Use the Name: field to assign it to the correct Customer.']"));
}
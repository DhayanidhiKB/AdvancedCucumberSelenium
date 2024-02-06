package com.my.salesforce.sandbox.application.elements.opportunities.creditassessment;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface CreditAssessmentPageElements {
    SelenideElement Header = $(By.xpath("//h2[text()='Credit Assessment']"));
    SelenideElement Reload = $(By.xpath("//button[@title='Reload status of credit assessment']"));
    String Option = "//span[text()='%value%']/preceding-sibling::span";
    SelenideElement Next = $(By.xpath("//lightning-button[@data-id='next']"));
    SelenideElement AssessmentStatus = $(By.xpath("//span[text()='Assessment Approval Status']/following-sibling::div/descendant::lightning-formatted-text[text()='Auto-Approved']"));
    SelenideElement AssessmentOutcome = $(By.xpath("//span[text()='Account Assessment Outcome']/following-sibling::div/descendant::a[contains(text(),'Credit Assess - ')]"));
    SelenideElement ClosedOpportunity = $(By.xpath("//span[text()='Credit Assessment cannot be created against closed opportunity. To proceed, create a new opportunity record.']/parent::lightning-formatted-rich-text"));
}
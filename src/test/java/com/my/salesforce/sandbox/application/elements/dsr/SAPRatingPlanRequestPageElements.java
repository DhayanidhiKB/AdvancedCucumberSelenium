package com.my.salesforce.sandbox.application.elements.dsr;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface SAPRatingPlanRequestPageElements {
    SelenideElement Header = $(By.xpath("//h2[text()='Create Rating Plan Activation Request']"));
    SelenideElement SubmitMessage = $(By.xpath("//b[text()='Are you sure you would like to submit the rating plan activation request?']"));
    SelenideElement SuccessMessage = $(By.xpath("//b[text()='Billing account creation request submitted successfully. You may close this window now.']/parent::h2"));
    String Button = "//button[text()='%value%']";
    SelenideElement Close = $(By.xpath("//footer[@class='slds-modal__footer']//button[text()='Close']"));
}
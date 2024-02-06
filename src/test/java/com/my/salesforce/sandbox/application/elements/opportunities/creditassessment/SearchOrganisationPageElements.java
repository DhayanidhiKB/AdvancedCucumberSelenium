package com.my.salesforce.sandbox.application.elements.opportunities.creditassessment;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface SearchOrganisationPageElements {
    SelenideElement iFrame = $(By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]"));
    SelenideElement CustomerAgreement = $(By.xpath("//label[text()='Customer Agreed']/preceding-sibling::input[@checked='checked']"));
    SelenideElement CustomerDisagreed = $(By.xpath("//label[text()='Customer Disagreed']/preceding-sibling::input"));
    String Option = "//input[@value='%value%']";
    SelenideElement SearchBusiness = $(By.xpath("//input[contains(@id,'OrganisationDetails')]"));
    String ViewBusiness = "//td[text()='%value%']/following-sibling::td//a[text()='View']";
    SelenideElement SelectOrganisation = $(By.xpath("//input[@value='Select']"));
    String Proceed = "//input[@value='%value%']";
    String Enter = "(//label[text()='%value1%']/parent::th/following-sibling::td//input)[%value2%]";
    SelenideElement IndustryType = $(By.xpath("//label[text()='Industry']/parent::th/following-sibling::td//select"));
    SelenideElement Decision = $(By.xpath("//label[text()='Credit Assessment Decision']/parent::th/following-sibling::td/label[text()='Auto-Approved']"));
}
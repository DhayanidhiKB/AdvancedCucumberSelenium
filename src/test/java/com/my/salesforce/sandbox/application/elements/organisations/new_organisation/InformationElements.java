package com.my.salesforce.sandbox.application.elements.organisations.new_organisation;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface InformationElements {
    SelenideElement LegalEntityName = $(By.xpath("//abbr[text()='*']/parent::label[text()='Legal Entity Name']/following-sibling::div//input"));
    SelenideElement ACN = $(By.xpath("//label[text()='ACN']/following-sibling::div//input"));
    SelenideElement ABN = $(By.xpath("//label[text()='ABN']/following-sibling::div//input"));
    SelenideElement StarTrackCreditLimit = $(By.xpath("//label[text()='StarTrack Approved Credit Limit']/following-sibling::div//input"));
}
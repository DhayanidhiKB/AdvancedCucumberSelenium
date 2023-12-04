package com.my.salesforce.sandbox.application.elements.contract.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface LodgementPointsAndAccountNumberPageElements {
    SelenideElement Header = $(By.xpath("//h1[@title='Lodgement Points and Account Numbers']"));
    SelenideElement Manage = $(By.xpath("//a[@title='Manage']/parent::li"));
    String action = "(//div[@title='%value1%']/parent::a)[%value2%]";
    SelenideElement Edit = $(By.xpath("//div[@title='Edit']/parent::a"));
    SelenideElement Status = $(By.xpath("//button[@data-value='Active']/parent::div"));
    String Button = "//button[text()='%value%']";
    String contract_link = "//h1[text()='Lodgement Points and Account Numbers']/preceding-sibling::lst-breadcrumbs//a[text()='%value%']";
    By AgreementLodgementPoint = By.xpath("//table[@aria-label='Lodgement Points and Account Numbers']/tbody//th//a");
    String label = "//label[text()='%value%']";
}
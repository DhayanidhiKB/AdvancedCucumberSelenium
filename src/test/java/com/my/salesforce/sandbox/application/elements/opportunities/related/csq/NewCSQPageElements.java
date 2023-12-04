package com.my.salesforce.sandbox.application.elements.opportunities.related.csq;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface NewCSQPageElements {
    SelenideElement Header = $(By.xpath("//h2[text()='New Customer Scoping Questionnaire (CSQ): CSQ']"));
    String TextArea = "//label[text()='%value%']/following-sibling::div//textarea";
    String Button = "//button[text()='%value%']";
}
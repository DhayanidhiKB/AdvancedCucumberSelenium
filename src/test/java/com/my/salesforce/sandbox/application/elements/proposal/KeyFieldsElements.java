package com.my.salesforce.sandbox.application.elements.proposal;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface KeyFieldsElements {
    SelenideElement SubHeader = $(By.xpath("(//h2[text()='Key Fields'])[2]"));
    String KeyContact = "//span[text()='Key Contact']/parent::div/following-sibling::div//a[text()='%value%']";
}
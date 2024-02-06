package com.my.salesforce.sandbox.application.elements.organisations.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface RelatedElements {
    SelenideElement Roles = $(By.xpath("//span[@title='Roles']/parent::a"));
    SelenideElement Assets = $(By.xpath("//span[@title='Assets']/parent::a"));
}
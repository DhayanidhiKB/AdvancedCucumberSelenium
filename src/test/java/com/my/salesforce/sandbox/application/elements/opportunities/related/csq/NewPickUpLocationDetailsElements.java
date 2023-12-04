package com.my.salesforce.sandbox.application.elements.opportunities.related.csq;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface NewPickUpLocationDetailsElements {
    SelenideElement Header = $(By.xpath("//div[text()='Pick-Up Location']/parent::h1"));
    String Action = "//runtime_platform_actions-action-renderer[@title='%value%']/parent::li";
}
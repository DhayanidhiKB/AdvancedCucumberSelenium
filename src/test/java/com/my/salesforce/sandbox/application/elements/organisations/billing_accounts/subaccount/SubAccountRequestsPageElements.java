package com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface SubAccountRequestsPageElements {
    SelenideElement Header = $(By.xpath("//h1[text()='Sub Account Requests']"));
    SelenideElement SelectAll = $(By.xpath("//span[text()='Select All']/ancestor::span"));
    String SubAccountRequestLink = "//th[@data-label='Sub Account Name']/descendant::span[text()='%value%']";
}
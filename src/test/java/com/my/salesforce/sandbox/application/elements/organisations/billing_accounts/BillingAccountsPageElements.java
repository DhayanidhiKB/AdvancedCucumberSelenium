package com.my.salesforce.sandbox.application.elements.organisations.billing_accounts;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface BillingAccountsPageElements {
    SelenideElement Header = $(By.xpath("//h1[text()='Billing Accounts']"));
    SelenideElement SelectAll = $(By.xpath("//span[text()='Select All']/ancestor::span"));
    SelenideElement FirstBillingAccount = $(By.xpath("(//th[@data-label='Billing Account Name']/descendant::span)[1]"));
}
package com.my.salesforce.sandbox.application.elements.organisations.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface OrganisationRelatedLinksElements {
    SelenideElement ShowAll = $(By.xpath("//a[contains(text(),'Show All')]"));
    SelenideElement Contacts = $(By.xpath("//a[contains(@href,'/related/Contacts/view')]"));
    SelenideElement BillingAccount = $(By.xpath("//a[contains(@href,'/related/Billing_Accounts__r/view')]"));
    SelenideElement SubAccountRequests = $(By.xpath("//a[contains(@href,'related/Sub_Accounts__r/view')]"));
}
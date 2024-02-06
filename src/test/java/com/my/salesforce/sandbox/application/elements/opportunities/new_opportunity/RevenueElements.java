package com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface RevenueElements {
    SelenideElement Header = $(By.xpath("//span[text()='Revenue']/parent::h3"));
    SelenideElement TotalOpportunityValue = $(By.xpath("//label[text()='Total Opportunity Value']/following-sibling::div/input"));
}
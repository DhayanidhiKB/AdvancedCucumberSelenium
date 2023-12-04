package com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface SummaryElements {
    SelenideElement Header = $(By.xpath("//span[text()='Opportunity Summary']"));
    SelenideElement Type = $(By.xpath("//label[text()='Type']/following-sibling::div/lightning-base-combobox"));
    String Option = "//span[@title='%value%']/ancestor::lightning-base-combobox-item";
}
package com.my.salesforce.sandbox.application.elements.opportunities.related;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface NewCompetitorAndIncumbentElements {
    SelenideElement Header = $(By.xpath("//h2[text()='New Competitor and Incumbent']"));
    SelenideElement CompetitorType = $(By.xpath("//label[text()='Competitor Type']/following-sibling::div//button"));
    SelenideElement Product = $(By.xpath("//label[text()='Product']/following-sibling::div//button"));
    SelenideElement CompetitorName = $(By.xpath("//label[text()='Competitor Name']/following-sibling::div//button"));
    SelenideElement CompetitiveStatus = $(By.xpath("//label[text()='Competitive Status']/following-sibling::div//button"));

    String Option = "//span[text()='%value%']/ancestor::lightning-base-combobox-item";
    SelenideElement CompetitiveAdvantage = $(By.xpath("//label[text()='Competitive Advantage']/following-sibling::div/textarea"));
    SelenideElement Save = $(By.xpath("//button[@name='SaveEdit']"));
}
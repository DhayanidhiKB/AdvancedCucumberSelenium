package com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface LegalEntityNameElements {
    SelenideElement Header = $(By.xpath("//h2[text()='Legal Entity Name']"));
    SelenideElement Search = $(By.xpath("//h2[text()='Legal Entity Name']/parent::div/following-sibling::div/descendant::input[@title='Search Organisations...']"));
    String Link = "//div[text()='Organisations']/parent::h2/ancestor::div[@data-aura-class='forceSearchResultsRegion']/descendant::a[@title='%value%']";
}
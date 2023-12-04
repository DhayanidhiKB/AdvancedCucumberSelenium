package com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface KeyContactElements {
    SelenideElement Header = $(By.xpath("//h2[text()='Key Contact']"));
    SelenideElement Search = $(By.xpath("//h2[text()='Key Contact']/parent::div/following-sibling::div/descendant::input[@title='Search Contacts...']"));
    String Link = "//h2[text()='Key Contact']/parent::div/following-sibling::div/descendant::a[@title='%value%']";
}
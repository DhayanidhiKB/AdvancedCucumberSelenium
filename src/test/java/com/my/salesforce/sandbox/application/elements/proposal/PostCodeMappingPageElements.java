package com.my.salesforce.sandbox.application.elements.proposal;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface PostCodeMappingPageElements {
    SelenideElement Header = $(By.xpath("//h2[text()='Post Code Mapping Results']"));
    SelenideElement Search = $(By.xpath("//h2[text()='Post Code Mapping Results']/parent::div/following-sibling::div/descendant::input[@title='Search Post Code Mapping']"));
    String Link = "//a[text()='%value%']";
}
package com.my.salesforce.sandbox.application.elements.dsr;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface PricingSupportRequestPageElements {
    SelenideElement Header = $(By.xpath("//h2[contains(text(),'Edit DS-')]"));
    SelenideElement EntityName = $(By.xpath("//label[text()='Legal Entity Name']/following-sibling::div"));
    String SearchBox = "//label[text()='%value%']/following-sibling::div//input";
    String ListBox = "//label[text()='%value%']/following-sibling::div//button";
    String Label = "//label[text()='%value%']//following-sibling::div/descendant::input";
    String DualListBox = "//div[text()='%value1%']/following-sibling::div/descendant::ul/li//span[@title='%value2%']";
    String Arrow = "//div[text()='%value1%']/following-sibling::div/descendant::button[@title='Move selection to %value2%']";
    String TextArea = "//label[text()='Sales Justification']/following-sibling::div/textarea";
    String Input = "//label[text()='%value%']/following-sibling::div/input";
    SelenideElement Returns = $(By.xpath("(//input[@name='Returns__c'])[2]"));
    String Button = "//runtime_platform_actions-action-renderer[@title='%value%']/parent::li";
}
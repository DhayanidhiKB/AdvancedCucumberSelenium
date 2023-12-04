package com.my.salesforce.sandbox.application.elements.dsr;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface SupportRequestsPageElements {
    SelenideElement Header = $(By.xpath("//span[text()='Deal Support Request']/ancestor::h2"));
    SelenideElement Title = $(By.xpath("//span[@title='Deal Support Request']"));
    SelenideElement DsrTable = $(By.xpath("//table[@aria-label='Deal Support Request']"));
    SelenideElement ViewAll = $(By.xpath("//span[@class='view-all-label']/parent::a"));
    ElementsCollection NoOfDSRs = $$(By.xpath("//th[@data-label='Deal Support Request ID']//span[starts-with(text(),'DS-')]"));
}
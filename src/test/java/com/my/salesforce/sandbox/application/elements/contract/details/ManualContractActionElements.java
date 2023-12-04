package com.my.salesforce.sandbox.application.elements.contract.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface ManualContractActionElements {
    SelenideElement ImportOfflineDocument = $(By.xpath("//span[text()='Import Offline Document']/parent::div/following-sibling::div/descendant::a"));
    SelenideElement MergeContractDocuments = $(By.xpath("//span[text()='Merge Contract Documents']/parent::div/following-sibling::div/descendant::a"));
}
package com.my.salesforce.sandbox.application.elements.contract;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface ContractPageElements {
    SelenideElement Header = $(By.xpath("//div[text()='Apttus Contract']"));
    SelenideElement Name = $(By.xpath("//div[text()='Apttus Contract']/following-sibling::slot/lightning-formatted-text"));
    String Related = "//p[@title='Related %value1%']/following-sibling::p/descendant::span[text()='%value2%']";
    String StageAhead = "//a[@data-tab-name='%value%' and @aria-selected='false']/parent::li";
    String CurrentStage = "//a[@data-tab-name='%value%' and @aria-selected='true']/parent::li";
    SelenideElement CreateContract = $(By.xpath("//span[text()='Create Contracts']/parent::div/following-sibling::div/descendant::a"));
    SelenideElement UnmergeDocument=$(By.xpath("//input[@type='checkbox' and @name='j_id0:j_id28:GenerateDocsPage:GenerateDocsFrom:TemplateBlock:j_id104']"));

    SelenideElement iFrame = $(By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]"));
    String Edit = "//button[@title='Edit %value%']";
    String ListBox = "//button[starts-with(@aria-label,'%value%')]";
    String ListMenus = "//lightning-base-combobox-item//span[@title='%value%']";
    String Button = "//button[text()='%value%']/ancestor::li";
    SelenideElement GenerateDocsForm = $(By.xpath("//form[contains(@id,'GenerateDocsFrom')]"));
    SelenideElement CreateContractDocuments = $(By.xpath("//input[@value='Create Contract Documents']"));
    SelenideElement MergeDocuments = $(By.xpath("//input[@value='Merge Documents']"));
    String QuickLinks = "//span[contains(text(),'%value%')]/ancestor::a";
    SelenideElement ShowAll = $(By.xpath("//a[contains(text(),'Show All')]"));
    String ContractSteps = "//span[text()='Contract Steps']/parent::div/following-sibling::div/descendant::lightning-formatted-text[text()='%value%']";
    String ContractStepStatus = "//span[text()='Contract Step Status']/parent::div/following-sibling::div/descendant::lightning-formatted-text[text()='%value%']";
    SelenideElement IsDocumentSigned = $(By.xpath("//input[@name='Is_Document_Signed__c']"));
    String Action = "//a[contains(@href,'actionName=%value%')]";
    SelenideElement DovLanguage = $(By.xpath("//a[contains(@href,'DoV_Languages')]"));
    SelenideElement RequestChanges = $(By.xpath("//span[text()='Request T&C Changes']/parent::div/following-sibling::div/descendant::a"));
}
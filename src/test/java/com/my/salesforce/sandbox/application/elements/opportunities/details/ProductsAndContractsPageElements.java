package com.my.salesforce.sandbox.application.elements.opportunities.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface ProductsAndContractsPageElements {
    SelenideElement Header = $(By.xpath("//span[@title='Products']"));
    SelenideElement AddProducts = $(By.xpath("//div[@title='Add Products']/ancestor::li"));
    SelenideElement SortProducts = $(By.xpath("//div[@title='Sort Products']/ancestor::li"));
    SelenideElement ShowMore = $(By.xpath("//li[@data-target-reveals='sfdc:CustomButton.OpportunityLineItem.Edit_Product_LWC']"));
    SelenideElement BulkEdit = $(By.xpath("//a[@data-target-selection-name='sfdc:CustomButton.OpportunityLineItem.Edit_Product_LWC']/parent::li"));
    SelenideElement iFrame = $(By.xpath("//iframe[@title='accessibility title']"));
    String ViewAllLink = "//span[@class='assistiveText' and text()='%value%']/ancestor::a";
    By Proposal = By.xpath("//th[@data-label='Proposal ID']/descendant::lightning-primitive-custom-cell");
    SelenideElement ProposalName = $(By.xpath("//th[@data-label='Proposal ID']/descendant::lightning-primitive-custom-cell/descendant::a//span"));
    By ApttusContract = By.xpath("//th[@data-label='Apttus Contract Name']");
}
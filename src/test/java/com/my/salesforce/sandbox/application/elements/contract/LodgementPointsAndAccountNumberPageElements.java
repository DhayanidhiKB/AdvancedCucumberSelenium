package com.my.salesforce.sandbox.application.elements.contract;

import org.openqa.selenium.By;

public interface LodgementPointsAndAccountNumberPageElements {
    By Header = By.xpath("//h1[@title='Lodgement Points and Account Numbers']");
    By Manage = By.xpath("//a[@title='Manage']/parent::li");
    By StatusColumn = By.xpath("//span[text()='Status']");
    By MoreActions = By.xpath("//span[text()='Active']/ancestor::td/following-sibling::td/descendant::a");
    By ActionMenus = By.xpath("//div[contains(@class,'visible positioned')]");
    String action = "(//div[@title='%value1%']/parent::a)[%value2%]";
    By Edit = By.xpath("//div[@title='Edit']/parent::a");
    By Status = By.xpath("//button[@data-value='Active']/parent::div");
    String Button = "//button[text()='%value%']";
    By BillingAccount = By.xpath("//label[text()='Billing Account']/parent::lightning-grouped-combobox/descendant::input");
    String contract_link = "//h1[text()='Lodgement Points and Account Numbers']/preceding-sibling::lst-breadcrumbs//a[text()='%value%']";
    By AgreementLodgementPoint = By.xpath("//table[@aria-label='Lodgement Points and Account Numbers']/tbody//th//a");
    String label = "//label[text()='%value%']";
}
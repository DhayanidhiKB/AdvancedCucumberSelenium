package com.my.salesforce.sandbox.application.elements.proposal;

import org.openqa.selenium.By;

public interface APPCProductAttributesPageElements {
    By iFrame = By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]");
    By Header = By.xpath("//span[text()='Australia Post Parcel Contract']/ancestor::h2");
    By SubHeader = By.xpath("//md-tab-item[contains(text(),'Product Attributes')]");
    By EvaluatedSpend = By.xpath("//input[@type='text' and @aria-invalid='true']");
    String DefaultAttribute = "//div[contains(text(),'%value%')]";
    String CubicStatus = "//span[starts-with(text(),'Helps determine the cubic status')]/ancestor::div[@class='tooltip']/parent::div/following-sibling::dynamic-field/div[contains(text(),'%value%')]";
    By Validate = By.xpath("//button[@buttonid='id_task_right_validatebundle']");
    By AddMoreProducts = By.xpath("//button[contains(@buttonid,'id_task_right_customaction')]");
    By ReviewCart = By.xpath("//button[@buttonid='id_task_right_gotopricing']");
    String Product = "//button[contains(text(),'%value%')]";
    By PSRMsg = By.xpath("//a[text()='1. This customer qualifies for pricing that is outside of sales delegation. A pricing support request (PSR) is required to proceed.']/ancestor::li");
    By HeaderCheckBox = By.xpath("//div[@id='cart-checkbox--header']");
    By ProductLink = By.xpath("//span[text()='Australia Post Parcel Contract']");
    By RefreshPricing = By.xpath("//button[contains(text(),'Refresh Pricing')]");
    By ConfigureIcon = By.xpath("//md-icon[contains(@aria-label,'lineAction:configuration')]");
    By MoreMenu = By.xpath("//span[@class='menu-toggle']");
    By ViewRateCard = By.xpath("//img[@alt='APT_View_Rate_Card']");
    By LinkPSR = By.xpath("//img[@alt='Link PSR']");
}
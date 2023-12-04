package com.my.salesforce.sandbox.application.elements.proposal;

import org.openqa.selenium.By;

public interface StarTrackProductAttributesPageElements {
    By iFrame = By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]");
    String TabItems = "//md-tab-item[contains(text(),'%value%')]";
    String ListBox = "(//span[text()='--None--'])[%value%]/parent::a";
    String Option = "//div[text()='%value%']/parent::div";
    String PrimaryPostcodeSubUrb = "//label[@class='product-attribute__name']/parent::div/following-sibling::dynamic-field//div[contains(text(),'%value%')]";
    String Tier = "//label[@class='product-attribute__name']/parent::div/following-sibling::dynamic-field//div[contains(text(),'%value%')]";
    String PostcodeSubUrb = "//a[@title='%value%']";
    By Validate = By.xpath("//button[@buttonid='id_task_right_validatebundle']");
    By ReviewCart = By.xpath("//button[@buttonid='id_task_right_gotopricing']");
    By AddMoreProducts1 = By.xpath("//button[contains(@buttonid,'id_task_right_customaction')]");
    By AddMoreProducts2 = By.xpath("//button[contains(text(),'Add More Products')]");
    String ProductNameBlock = "//span[text()='%value%']";
    String Product = "//button[contains(text(),'%value%')]";
    By NOLPRequired = By.xpath("//img[@alt='No LP Required']");
    By MoreMenu = By.xpath("//span[@class='menu-toggle']");
    By ViewRateCard = By.xpath("//img[@alt='APT_View_Rate_Card']");
}
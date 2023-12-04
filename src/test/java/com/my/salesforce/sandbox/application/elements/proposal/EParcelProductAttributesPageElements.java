package com.my.salesforce.sandbox.application.elements.proposal;

import org.openqa.selenium.By;

public interface EParcelProductAttributesPageElements {
    By iFrame = By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]");
    String TabItems = "//md-tab-item[contains(text(),'%value%')]";
    String ListBox = "(//span[text()='--None--'])[%value%]/parent::a";
    String ListBoxMenu = "//div[text()='%value%']/parent::div";
    String ExpressPriceStructure = "//a[@title='BANDED {Z6}']";
    By LodgementPostcode = By.xpath("//label[@class='product-attribute__name']/ancestor::div//dynamic-field//input[@aria-invalid='true']");
    By TransitCover = By.xpath("(//span[contains(text(),'Feature Variables')]/parent::h3/following-sibling::div//input)[4]");
    By Validate = By.xpath("//button[@buttonid='id_task_right_validatebundle']");
    By ReviewCart = By.xpath("//button[@buttonid='id_task_right_gotopricing']");
    By AddMoreProducts = By.xpath("//button[contains(@buttonid,'id_task_right_customaction')]");
    By MoreMenu = By.xpath("//span[@class='menu-toggle']");
    String ProductNameBlock = "//span[text()='%value%']";
    String Product = "//button[contains(text(),'%value%')]";
    String AddLP = "//span[text()='%value%']/ancestor::div[@class='ui-grid-contents-wrapper']/descendant::img[@alt='Add LP' and contains(@src,'Red_Cart')]";
    By enterRKCode = By.xpath("(//img[@alt='Get P&Y Rate Card'])[2]");
}
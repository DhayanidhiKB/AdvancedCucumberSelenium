package com.my.salesforce.sandbox.application.elements.shopping_cart.eparcel;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public interface EParcelProductAttributesPageElements {
    SelenideElement iFrame = $(By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]"));
    String TabItems = "//md-tab-item[contains(text(),'%value%')]";
    String ListBox = "(//span[text()='--None--'])[%value%]/parent::a";
    String ListBoxMenu = "//div[text()='%value%']/parent::div";
    String ExpressPriceStructure = "//a[@title='BANDED {Z6}']";
    SelenideElement LodgementPostcode = $(By.xpath("//label[@class='product-attribute__name']/ancestor::div//dynamic-field//input[@aria-invalid='true']"));
    SelenideElement TransitCover = $(By.xpath("(//span[contains(text(),'Feature Variables')]/parent::h3/following-sibling::div//input)[4]"));
    SelenideElement Validate = $(By.xpath("//button[@buttonid='id_task_right_validatebundle']"));
    SelenideElement ReviewCart = $(By.xpath("//button[@buttonid='id_task_right_gotopricing']"));
    SelenideElement AddMoreProducts = $(By.xpath("//button[contains(@buttonid,'id_task_right_customaction')]"));
    SelenideElement MoreMenu = $(By.xpath("//span[@class='menu-toggle']"));
    String ProductNameBlock = "//span[text()='%value%']";
    String Product = "//button[normalize-space()='%value%']";

    String ConfigureIcon = "//span[text()='%value%']/ancestor::span[@class='product-name-space']/following-sibling::div//md-icon[contains(@aria-label,'lineAction:configuration')]";
    String AddLP = "//span[text()='%value%']/ancestor::div[@class='ui-grid-contents-wrapper']/descendant::img[@alt='Add LP' and contains(@src,'Red_Cart')]";
    SelenideElement enterRKCode = $(By.xpath("(//img[@alt='Get P&Y Rate Card'])[2]"));
    SelenideElement ProgressBar = $(By.xpath("//progress-bar[@id='progress-bar']/descendant::div[@id='ngProgress']"));
    String CheckBox = "//span[text()='%value%']/ancestor::div[@ng-style='row.entity.rowStyle']/preceding-sibling::div[1]/descendant::div[@class='grid-checkbox']";
    SelenideElement Delete = $(By.xpath("//button[@aria-label='Delete']"));
    SelenideElement SubmitForApproval = $(By.xpath("//button[contains(text(),'Submit for Approval')]"));
}
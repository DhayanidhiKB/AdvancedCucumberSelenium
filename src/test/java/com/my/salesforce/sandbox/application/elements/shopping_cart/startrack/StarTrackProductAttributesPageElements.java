package com.my.salesforce.sandbox.application.elements.shopping_cart.startrack;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface StarTrackProductAttributesPageElements {
    SelenideElement iFrame = $(By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]"));
    String TabItems = "//md-tab-item[contains(text(),'%value%')]";
    String ListBox = "(//span[text()='--None--'])[%value%]/parent::a";
    String Option = "//div[text()='%value%']/parent::div";
    String PrimaryPostcodeSubUrb = "//label[@class='product-attribute__name']/parent::div/following-sibling::dynamic-field//div[contains(text(),'%value%')]";
    String Tier = "//label[@class='product-attribute__name']/parent::div/following-sibling::dynamic-field//div[contains(text(),'%value%')]";
    String PostcodeSubUrb = "//a[@title='%value%']";
    SelenideElement Validate = $(By.xpath("//button[@buttonid='id_task_right_validatebundle']"));
    SelenideElement ReviewCart = $(By.xpath("//button[@buttonid='id_task_right_gotopricing']"));
    SelenideElement AddMoreProducts = $(By.xpath("//button[contains(@buttonid,'id_task_right_customaction') and contains(text(),'Add More Products')]"));
    String ProductNameBlock = "//span[text()='%value%']";
    String Product = "//button[contains(text(),'%value%')]";
    SelenideElement ConfigureIcon = $(By.xpath("//md-icon[contains(@aria-label,'lineAction:configuration')]"));
    SelenideElement ViewRateCard = $(By.xpath("//img[@alt='APT_View_Rate_Card']"));
    SelenideElement NoLPRequired = $(By.xpath("//img[@alt='No LP Required']"));
    SelenideElement ProgressBar = $(By.xpath("//progress-bar[@id='progress-bar']/descendant::div[@id='ngProgress']"));
}
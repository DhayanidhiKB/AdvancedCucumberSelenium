package com.my.salesforce.sandbox.application.elements.shopping_cart.appc;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface APPCProductAttributesPageElements {
    SelenideElement iFrame = $(By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]"));
    SelenideElement Header = $(By.xpath("//span[text()='Australia Post Parcel Contract']/ancestor::h2"));
    SelenideElement SubHeader = $(By.xpath("//md-tab-item[contains(text(),'Product Attributes')]"));
    SelenideElement EvaluatedSpend = $(By.xpath("//input[@type='text' and @aria-invalid='true']"));
    SelenideElement TransitCoverAmount = $(By.xpath("//span[contains(text(),'Features, Returns and Additional Charges')]/parent::h3/following-sibling::div//input"));
    String DefaultAttribute = "//div[contains(text(),'%value%')]";
    String CubicStatus = "//span[starts-with(text(),'Helps determine the cubic status')]/ancestor::div[@class='tooltip']/parent::div/following-sibling::dynamic-field/div[contains(text(),'%value%')]";
    SelenideElement Validate = $(By.xpath("//button[@buttonid='id_task_right_validatebundle']"));
    SelenideElement AddMoreProducts = $(By.xpath("//button[contains(text(),'Add More Products')]"));
    SelenideElement ReviewCart = $(By.xpath("//button[@buttonid='id_task_right_gotopricing']"));
    By RefreshPricing = By.xpath("(//button[contains(text(),'Refresh Pricing')])[1]");
    SelenideElement Checkout = $(By.xpath("(//button[contains(text(),'Checkout')])[1]"));
    SelenideElement HeaderCheckBox = $(By.xpath("//div[@id='cart-checkbox--header']"));
    SelenideElement ProductLink = $(By.xpath("//span[text()='Australia Post Parcel Contract']"));
    SelenideElement ConfigureIcon = $(By.xpath("//md-icon[contains(@aria-label,'lineAction:configuration')]"));
    SelenideElement MoreMenu = $(By.xpath("//span[@class='menu-toggle']"));
    SelenideElement ViewRateCard = $(By.xpath("//img[@alt='APT_View_Rate_Card']"));
    SelenideElement PSRRequiredMessage = $(By.xpath("//li[contains(@ng-repeat,'pageErrors')]/descendant::a[contains(text(),'This customer qualifies for pricing that is outside of sales delegation. A pricing support request (PSR) is required to proceed.')]"));
    SelenideElement LinkPSR = $(By.xpath("//img[@alt='Link PSR']"));
    SelenideElement ProgressBar = $(By.xpath("//progress-bar[@id='progress-bar']/descendant::div[@id='ngProgress']"));
    String Product = "//button[contains(text(),'%value%')]";
}
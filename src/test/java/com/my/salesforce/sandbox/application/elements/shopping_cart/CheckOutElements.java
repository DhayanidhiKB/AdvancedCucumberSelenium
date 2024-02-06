package com.my.salesforce.sandbox.application.elements.shopping_cart;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface CheckOutElements {
    SelenideElement iFrame = $(By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]"));
    SelenideElement InnerIFrame = $(By.xpath("//iframe[contains(@src,'/APT_CheckOut?')]"));
    SelenideElement Header = $(By.xpath("//div[text()='Checkout']"));
    SelenideElement SubHeader = $(By.xpath("//div[text()='Select an option']"));
    SelenideElement ErrorMessage = $(By.xpath("//h2[text()='The product(s) in this shopping cart requires pricing to be configured before you can checkout.']/parent::div"));
    SelenideElement Close = $(By.xpath("//span[@ng-click='customAction.close()']"));
    ElementsCollection NoOfOptions = $$(By.xpath("//div[@data-id='disableSection']/child::div"));
    String Option = "//h3[@title='%value%']/parent::div";
}
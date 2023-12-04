package com.my.salesforce.sandbox.application.elements.contract.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface EditLodgementPointPopUpElements {
    SelenideElement iFrame = $(By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]"));
    SelenideElement Pop = $(By.xpath("//div[@class='customPopup']"));
    SelenideElement Header = $(By.xpath("//h3[text()='Edit Lodgement Point']"));

    SelenideElement SearchButton=$(By.xpath("//img[@alt='Account Name Lookup (New Window)']"));
    String SearchOption = "//b[text()='%value%']/following-sibling::a/img";
    String Button = "//input[@value='%value%']";
    SelenideElement Account = $(By.xpath("//table[@class='detailList']/descendant::a"));
}
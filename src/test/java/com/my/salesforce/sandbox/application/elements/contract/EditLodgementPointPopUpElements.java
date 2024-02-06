package com.my.salesforce.sandbox.application.elements.contract;

import org.openqa.selenium.By;

public interface EditLodgementPointPopUpElements {
    By iFrame = By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]");
    By Pop = By.xpath("//div[@class='customPopup']");
    By Header = By.xpath("//h3[text()='Edit Lodgement Point']");
    String SearchOption = "//b[text()='%value%']/following-sibling::a/img";
    String Button = "//input[@value='%value%']";
    By Account = By.xpath("//table[@class='detailList']/descendant::a");
}
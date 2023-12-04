package com.my.salesforce.sandbox.application.elements.contract.details;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface ImportOfflineDocumentElements {
    SelenideElement iFrame = $(By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]"));
    SelenideElement Header = $(By.xpath("//h2[contains(text(),'Import Offline Document')]"));
    SelenideElement SelectFile = $(By.xpath("//span[contains(text(),'Select the File')]/following-sibling::input"));
    SelenideElement AttachFile = $(By.xpath("//span[contains(text(),'Attach File')]/following-sibling::input"));
    SelenideElement Continue = $(By.xpath("//input[@value='Continue' and @type='button']"));
    SelenideElement Return = $(By.xpath("//input[@value='Return' and @type='submit']"));
}
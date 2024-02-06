package com.my.salesforce.sandbox.application.pages.contract.details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.details.ImportOfflineDocumentElements;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class ImportOfflineDocument extends BasePage implements ImportOfflineDocumentElements {
    public ImportOfflineDocument is_ready() {
        switch_to_frame(iFrame);
        isItVisible(Header);
        isItInteractable(SelectFile);
        isItInteractable(AttachFile);
        return this;
    }

    public ImportOfflineDocument upload_and_attach(String path) {
        $(SelectFile).uploadFile(new File(path));
        clickOn(AttachFile);
        return this;
    }

    public void proceed() {
        isItInteractable(Continue);
        actions().pause(Duration.ofSeconds(3)).click($(Continue)).build().perform();
        isItInteractable(Return);
        actions().pause(Duration.ofSeconds(3)).click($(Return)).build().perform();
    }

    public ImportOfflineDocument(WebDriver driver) {
        super(driver);
    }
}
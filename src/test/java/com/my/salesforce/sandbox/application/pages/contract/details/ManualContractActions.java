package com.my.salesforce.sandbox.application.pages.contract.details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.details.ManualContractActionElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class ManualContractActions extends BasePage implements ManualContractActionElements {
    public ManualContractActions is_ready() {
        isItInteractable(ImportOfflineDocument);
        isItInteractable(MergeContractDocuments);
        return this;
    }

    public void import_offline_document(String filePath) {
        clickOn(ImportOfflineDocument);
        getImportOfflineDocument().is_ready().upload_and_attach(filePath).proceed();
    }

    public void merge_documents() {
        clickOn(MergeContractDocuments);
    }

    private ImportOfflineDocument importOfflineDocument;

    public ManualContractActions(WebDriver driver) {
        super(driver);
        setImportOfflineDocument(new ImportOfflineDocument(lDriver));
    }
}
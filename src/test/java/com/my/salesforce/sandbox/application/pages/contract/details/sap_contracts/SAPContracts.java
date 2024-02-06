package com.my.salesforce.sandbox.application.pages.contract.details.sap_contracts;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.details.SAPContractsPageElements;
import lombok.*;

import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class SAPContracts extends BasePage implements SAPContractsPageElements {
    public SAPContracts is_ready() {
        isItVisible($(Header));
        isItInteractable($(SapContract));
        return this;
    }

    private SAPContract sapContractPage;

    public SAPContracts(WebDriver driver) {
        super(driver);
        setSapContractPage(new SAPContract(lDriver));
    }
}
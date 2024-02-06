package com.my.salesforce.sandbox.application.pages.contract.details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.ContractPageElements;
import com.my.salesforce.sandbox.application.pages.contract.details.lodgement_point.LodgementPointsAndAccountNumber;
import com.my.salesforce.sandbox.application.pages.contract.details.sap_contracts.SAPContracts;
import com.my.salesforce.sandbox.application.pages.contract.dov.DOVLanguages;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class ContractRelatedLinks extends BasePage implements ContractPageElements {
    public ContractRelatedLinks open_dov_language() {
        isItInteractable(locate(StageAhead, "In Signatures")).scrollIntoView(true);
        clickOn(DovLanguage);
        return this;
    }

    private DOVLanguages dovLanguages;
    private LodgementPointsAndAccountNumber lodgementPointsAndAccountNumberPage;
    private SAPContracts sapContractsPage;
    private ContractLineItems contractLineItemsPage;

    public ContractRelatedLinks(WebDriver driver) {
        super(driver);
        setSapContractsPage(new SAPContracts(lDriver));
        setContractLineItemsPage(new ContractLineItems(lDriver));
        setLodgementPointsAndAccountNumberPage(new LodgementPointsAndAccountNumber(lDriver));
        setDovLanguages(new DOVLanguages(lDriver));
    }
}
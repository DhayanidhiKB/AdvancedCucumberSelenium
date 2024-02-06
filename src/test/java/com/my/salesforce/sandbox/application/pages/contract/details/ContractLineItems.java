package com.my.salesforce.sandbox.application.pages.contract.details;

import com.codeborne.selenide.Selenide;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.details.ContractLineItemsPageElements;
import com.my.salesforce.sandbox.application.pages.contract.AgreementProductAttributeValue;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class ContractLineItems extends BasePage implements ContractLineItemsPageElements {
    public ContractLineItems is_ready() {
        isItVisible(Header);
        isItInteractable(locate(Controls, "Refresh"));
        isItInteractable(locate(Controls, "Show quick filters"));
        return this;
    }

    public void back_to_contract() {
        Selenide.back();
    }

    private AgreementProductAttributeValue agreementProductAttributeValue;

    public ContractLineItems(WebDriver driver) {
        super(driver);
        setAgreementProductAttributeValue(new AgreementProductAttributeValue(lDriver));
    }
}
package com.my.salesforce.sandbox.application.pages.contract.details.sap_contracts;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.details.SAPContractPageElements;
import com.my.salesforce.sandbox.application.pages.contract.BillingAccount;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class SAPContract extends BasePage implements SAPContractPageElements {
    public SAPContract is_ready() {
        isItVisible(Header);
        isItInteractable(PrintableView);
        return this;
    }

    public SAPContract verify(String field, String value) {
        locate(Edit, "SAP Contract Number").scrollIntoView(true);
        isItVisible(locate(Text, field, value));
        return this;
    }

    private BillingAccount billingAccountPage;

    public SAPContract(WebDriver driver) {
        super(driver);
        setBillingAccountPage(new BillingAccount(lDriver));
    }
}
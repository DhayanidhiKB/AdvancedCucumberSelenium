package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.BillingAccountRelatedTabElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class BillingAccountRelated extends BasePage implements BillingAccountRelatedTabElements {
    public BillingAccountRelated is_ready() {
        isItInteractable(AuthorizationRules);
        isItInteractable(NewAuthorizationRule);
        return this;
    }

    private BillingAccountDetails billingAccountDetailsPage;

    public BillingAccountRelated(WebDriver driver) {
        super(driver);
        setBillingAccountDetailsPage(new BillingAccountDetails(lDriver));
    }
}
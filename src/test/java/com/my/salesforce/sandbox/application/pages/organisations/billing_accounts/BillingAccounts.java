package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.BillingAccountsPageElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class BillingAccounts extends BasePage implements BillingAccountsPageElements {
    public BillingAccounts is_ready() {
        isItVisible(Header);
        isItInteractable(SelectAll);
        isItInteractable(FirstBillingAccount);
        return this;
    }

    public BillingAccounts open_first_billing_account_details() {
        clickOn(FirstBillingAccount);
        return this;
    }

    private BillingAccountDetails billingAccountDetailsPage;

    public BillingAccounts(WebDriver driver) {
        super(driver);
        setBillingAccountDetailsPage(new BillingAccountDetails(lDriver));
    }
}
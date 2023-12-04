package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.BillingAccountDetailsPageElements;
import com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.new_subaccount.NewSubAccountRequest;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class BillingAccountDetails extends BasePage implements BillingAccountDetailsPageElements {
    public BillingAccountDetails is_ready() {
        isItVisible(Header);
        isItInteractable(SubAccountRequest);
        isItInteractable(MoreActions);
        return this;
    }

    public BillingAccountDetails verify(String org_name, String abn) {
        shouldHaveText(ABN, abn);
        isItVisible(locate(Customer, org_name));
        return this;
    }

    public String getAccountNumber() {
        return isItVisible(BillingAccountNumber).getText();
    }

    public BillingAccountDetails sub_account_request() {
        clickOn(SubAccountRequest);
        return this;
    }

    public BillingAccountDetails related() {
        clickOn(RelatedTab);
        return this;
    }

    private NewSubAccountRequest newSubAccountRequestPage;

    public BillingAccountDetails(WebDriver driver) {
        super(driver);
        setNewSubAccountRequestPage(new NewSubAccountRequest(lDriver));
    }
}
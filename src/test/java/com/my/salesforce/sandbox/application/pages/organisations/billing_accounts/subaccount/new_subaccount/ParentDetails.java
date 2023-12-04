package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.new_subaccount;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount.NewSubAccountRequestPageElements;
import com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.new_subaccount.request_details.RequestDetails;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class ParentDetails extends BasePage implements NewSubAccountRequestPageElements {
    public ParentDetails verify(String proposal_name, String org_name) {
        shouldHaveText(locate(Labels, "Parent Proposal Name"), proposal_name);
        shouldHaveText(locate(Labels, "Legal Entity Name"), org_name);
        shouldHaveText(locate(Labels, "Sub Account Request Source"), "Proposal");
        return this;
    }

    public ParentDetails verify_account_number(String account_number) {
        shouldHaveText(ParentBillingAccountNumber, account_number);
        return this;
    }

    public ParentDetails verify_source() {
        shouldHaveText(SubAccountRequestSource, "Billing Account");
        return this;
    }

    private RequestDetails requestDetails;

    public ParentDetails(WebDriver driver) {
        super(driver);
        setRequestDetails(new RequestDetails(lDriver));
    }
}
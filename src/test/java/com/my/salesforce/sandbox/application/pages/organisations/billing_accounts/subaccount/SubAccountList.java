package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount.SubAccountListPageElements;
import com.my.salesforce.sandbox.application.pages.opportunities.creditassessment.FinalizeSubAccountRequest;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class SubAccountList extends BasePage implements SubAccountListPageElements {
    public SubAccountList is_ready() {
        isItVisible(Header);
        isItInteractable(locate(Button, "Add New Sub Account Request", "1"));
        isItInteractable(locate(Button, "Delete Sub Account Request", "1"));
        isItInteractable(SelectAll);
        return this;
    }

    public void submit_sub_account_request() {
        clickOn(SelectAll);
        clickOn(locate(Button, "Submit Request", "1"));
        isItVisible(SubHeader);
        isItInteractable(locate(Button, "Cancel", "1"));
        clickOn(locate(Button, "Submit", "1"));
        isItVisible(SuccessMessage);
        clickOn(Close);
    }

    public void finalize_request() {
        clickOn(SelectAll);
        clickOn(FinalizeRequest);
        getFinalizeSubAccountRequest().is_ready().finalize_request();
    }

    private FinalizeSubAccountRequest finalizeSubAccountRequest;

    public SubAccountList(WebDriver driver) {
        super(driver);
        setFinalizeSubAccountRequest(new FinalizeSubAccountRequest(lDriver));
    }
}
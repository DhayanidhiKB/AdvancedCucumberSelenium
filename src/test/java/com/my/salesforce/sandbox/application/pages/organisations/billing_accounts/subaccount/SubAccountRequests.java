package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount.SubAccountRequestsPageElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class SubAccountRequests extends BasePage implements SubAccountRequestsPageElements {
    public SubAccountRequests is_ready() {
        isItVisible(Header);
        isItInteractable(SelectAll);
        return this;
    }

    public void validate_sub_account_request(String sub_account_name, String add_contract_rates,
                                             String parcel_send_login, String product, String status) {
        clickOn(locate(SubAccountRequestLink, sub_account_name));
        getSubAccountRequestDetailsPage().is_ready().verify(add_contract_rates,
                parcel_send_login, product, status);
        is_ready();
    }

    private SubAccountRequestDetails subAccountRequestDetailsPage;

    public SubAccountRequests(WebDriver driver) {
        super(driver);
        setSubAccountRequestDetailsPage(new SubAccountRequestDetails(lDriver));
    }
}
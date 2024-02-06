package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount;

import com.codeborne.selenide.Selenide;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount.SubAccountRequestDetailsPageElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class SubAccountRequestDetails extends BasePage implements SubAccountRequestDetailsPageElements {
    public SubAccountRequestDetails is_ready() {
        isItVisible(Header);
        isItInteractable(NewNote);
        isItInteractable(SubmitForApproval);
        return this;
    }

    public SubAccountRequestDetails verify(String add_contract_rates, String parcel_send_login,
                                           String product, String status) {
        shouldHaveText(locate(Verify, "Sub Account Request Status"), status);
        shouldHaveText(locate(Verify, "Sub Account Request Status Description"), status);
        shouldHaveText(locate(Verify, "Add Contract Rates?"), add_contract_rates);
        shouldHaveText(locate(Verify, "Is Parcel Send Login Required?"), parcel_send_login);
        shouldHaveText(locate(Verify, "Product"), product);
        Selenide.back();
        return this;
    }

    public SubAccountRequestDetails(WebDriver driver) {
        super(driver);
    }
}
package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.new_subaccount;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount.NewSubAccountRequestPageElements;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

public class AlertWindow extends BasePage implements NewSubAccountRequestPageElements {
    public void handle_alert(@NotNull String contract_relationship) {
        if (contract_relationship.equals("Billing Account")) {
            isItVisible(Alert);
            isItVisible(CreateOpportunityMessage);
            clickOn(locate(Button, "OK"));
        }
    }

    public AlertWindow(WebDriver driver) {
        super(driver);
    }
}
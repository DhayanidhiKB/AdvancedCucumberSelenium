package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.new_subaccount.request_details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount.NewSubAccountRequestPageElements;
import org.openqa.selenium.WebDriver;

public class SubAccountNames extends BasePage implements NewSubAccountRequestPageElements {
    public SubAccountNames enter_name_as(String name) {
        setValue(name, locate(Enter, "Sub Account Name"));
        return this;
    }

    public void enter_name1_as(String name1) {
        setValue(name1, locate(Enter, "Account Name 2"));
    }

    public SubAccountNames(WebDriver driver) {
        super(driver);
    }
}
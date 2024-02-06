package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.new_subaccount.request_details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount.NewSubAccountRequestPageElements;
import org.openqa.selenium.WebDriver;

public class ELMS extends BasePage implements NewSubAccountRequestPageElements {
    public void set(String eLMS) {
        locate(Enter, "Sub Account Name").scrollIntoView(true);
        clickOn(locate(DropDown, "eLMS/eLMS Enabled"));
        clickOn(locate(Option, "eLMS/eLMS Enabled", eLMS));
    }

    public ELMS(WebDriver driver) {
        super(driver);
    }
}
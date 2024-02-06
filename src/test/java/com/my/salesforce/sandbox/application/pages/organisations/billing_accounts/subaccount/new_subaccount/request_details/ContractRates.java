package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.new_subaccount.request_details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount.NewSubAccountRequestPageElements;
import org.openqa.selenium.WebDriver;

public class ContractRates extends BasePage implements NewSubAccountRequestPageElements {
    public void set(String contract_rates) {
        clickOn(locate(DropDown, "Add Contract Rates?"));
        clickOn(locate(Option, "Add Contract Rates?", contract_rates));
    }

    public void choose(String product) {
        clickOn(locate(DualListBox, "Product", product));
        clickOn(locate(Arrow, "Product", "Chosen"));
    }

    public ContractRates(WebDriver driver) {
        super(driver);
    }
}
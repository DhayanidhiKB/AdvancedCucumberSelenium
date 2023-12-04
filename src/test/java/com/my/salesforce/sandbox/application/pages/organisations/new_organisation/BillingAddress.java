package com.my.salesforce.sandbox.application.pages.organisations.new_organisation;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.new_organisation.BillingAddressElements;
import org.openqa.selenium.WebDriver;

public class BillingAddress extends BasePage implements BillingAddressElements {
    public BillingAddress is_ready() {
        isItInteractable(BillingStreet);
        return this;
    }

    public void enter(String street, String city, String state,
                      String postal_code, String country) {
        setValue(street, BillingStreet);
        setValue(city, BillingCity);
        setValue(state, BillingState);
        setValue(postal_code, BillingPostalCode);
        setValue(country, BillingCountry);
    }

    public BillingAddress(WebDriver driver) {
        super(driver);
    }
}
package com.my.salesforce.sandbox.application.pages.organisations.new_organisation;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.new_organisation.PhysicalAddressElements;
import org.openqa.selenium.WebDriver;

public class PhysicalAddress extends BasePage implements PhysicalAddressElements {
    public PhysicalAddress is_ready() {
        isItInteractable(PhysicalStreet);
        return this;
    }

    public void enter(String street, String city, String state,
                      String postal_code, String country) {
        setValue(street, PhysicalStreet);
        setValue(city, PhysicalCity);
        setValue(state, PhysicalState);
        setValue(postal_code, PhysicalPostalCode);
        setValue(country, PhysicalCountry);
    }

    public PhysicalAddress(WebDriver driver) {
        super(driver);
    }
}
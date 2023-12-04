package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.new_subaccount;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount.NewSubAccountRequestPageElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class AddressOfBusiness extends BasePage implements NewSubAccountRequestPageElements {
    public AddressOfBusiness is_ready() {
        clickOn(locate(SubHeader, "Address of Business"));
        return this;
    }

    public AddressOfBusiness enter(String address_line_1, String address_line_2, String sub_urb,
                                   String state, String post_code) {
        locate(DropDown, "eLMS/eLMS Enabled").scrollIntoView(true);
        clickOn(AddressToggle);
        setValue(address_line_1, locate(Enter, "Address Line 1"));
        setValue(address_line_2, locate(Enter, "Address Line 2"));
        setValue(sub_urb, locate(Enter, "Suburb"));
        setValue(state, locate(Enter, "State"));
        setValue(post_code, locate(Enter, "Postcode"));
        isItInteractable(Confirm).scrollIntoView(true).click();
        isItInteractable(Confirmed);
        return this;
    }

    private OutletDetails outletDetails;

    public AddressOfBusiness(WebDriver driver) {
        super(driver);
        setOutletDetails(new OutletDetails(lDriver));
    }
}
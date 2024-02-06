package com.my.salesforce.sandbox.application.pages.organisations.new_organisation;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.new_organisation.NewOrganisationElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class NewOrganisation extends BasePage implements NewOrganisationElements {
    public NewOrganisation is_ready() {
        isItVisible(Header);
        return this;
    }

    public NewOrganisation enter_information(String name, String abn, String acn, String limit) {
        getInformation().is_ready().enter_legal_entity_name_as(name)
                .enter_acn_as(acn).enter_abn_as(abn).enter_credit(limit);
        return this;
    }

    public NewOrganisation enter_billing_address(String street, String city, String state, String postal_code, String country) {
        getBillingAddress().is_ready().enter(street, city, state, postal_code, country);
        return this;
    }

    public NewOrganisation enter_physical_address(String street, String city, String state, String postal_code, String country) {
        getPhysicalAddress().is_ready().enter(street, city, state, postal_code, country);
        return this;
    }

    public NewOrganisation save() {
        clickOn(Save);
        return this;
    }

    public NewOrganisation validate_error() {
        isItInteractable(LegalEntityNameError);
        clickOn(ErrorIcon);
        return this;
    }

    private Information information;
    private BillingAddress billingAddress;
    private PhysicalAddress physicalAddress;

    public NewOrganisation(WebDriver driver) {
        super(driver);
        setInformation(new Information(lDriver));
        setBillingAddress(new BillingAddress(lDriver));
        setPhysicalAddress(new PhysicalAddress(lDriver));
    }
}
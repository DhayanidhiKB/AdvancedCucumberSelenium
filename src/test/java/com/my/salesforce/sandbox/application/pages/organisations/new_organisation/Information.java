package com.my.salesforce.sandbox.application.pages.organisations.new_organisation;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.new_organisation.InformationElements;
import org.openqa.selenium.WebDriver;

public class Information extends BasePage implements InformationElements {
    public Information is_ready() {
        isItInteractable(LegalEntityName);
        isItInteractable(ACN);
        isItInteractable(ABN);
        return this;
    }

    public Information enter_legal_entity_name_as(String legal_entity_name) {
        setValue(legal_entity_name, LegalEntityName);
        return this;
    }

    public Information enter_acn_as(String acn) {
        setValue(acn, ACN);
        return this;
    }

    public Information enter_abn_as(String abn) {
        setValue(abn, ABN);
        return this;
    }

    public void enter_credit(String limit) {
        setValue(limit, StarTrackCreditLimit);
    }

    public Information(WebDriver driver) {
        super(driver);
    }
}
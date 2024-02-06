package com.my.salesforce.sandbox.application.pages.opportunities.creditassessment;

import com.codeborne.selenide.Selenide;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.creditassessment.SearchOrganisationPageElements;
import com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.new_subaccount.NewSubAccountRequest;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class SearchOrganisation extends BasePage implements SearchOrganisationPageElements {
    public SearchOrganisation is_it_ready() {
        switch_to_frame(iFrame);
        isItInteractable(locate(Proceed, "Cancel"));
        isItInteractable(locate(Proceed, "Next"));
        return this;
    }

    public void action(@NotNull String action) {
        switch (action) {
            case "Cancel":
                clickOn(locate(Proceed, "Cancel"));
                break;
            case "Customer Disagreed":
                clickOn(locate(Proceed, "Next"));
                isItInteractable(CustomerAgreement);
                clickOn(CustomerDisagreed);
                clickOn(locate(Proceed, "Continue"));
                break;
        }
    }

    public SearchOrganisation search(String abn) {
        clickOn(locate(Proceed, "Next"));
        isItInteractable(CustomerAgreement);
        clickOn(locate(Proceed, "Continue"));
        isItInteractable(locate(Proceed, "Business Name"));
        isItInteractable(locate(Proceed, "Search"));
        clickOn(locate(Option, "ABN"));
        setValue(abn, SearchBusiness);
        clickOn(locate(Proceed, "Search"));
        isItInteractable(SearchBusiness);
        return this;
    }

    public SearchOrganisation select(String abn) {
        clickOn(locate(ViewBusiness, abn));
        clickOn(SelectOrganisation);
        return this;
    }

    public SearchOrganisation credit_assess_using(String contact, String email,
                                                  String street_number, String industry) {
        isItInteractable(IndustryType).selectOptionContainingText(industry);
        setValue(contact, locate(Enter, "Contact Name", "1"));
        setValue(email, locate(Enter, "Contact Email Address", "1"));
        setValue(street_number, locate(Enter, "Street Number", "1"));
        isItInteractable(locate(Enter, "Street Number", "2"))
                .scrollIntoView(true).setValue(street_number);
        clickOn(locate(Proceed, "Credit Assessment"));
        return this;
    }

    public void save_assessment() {
        isItInteractable(Decision);
        clickOn(locate(Proceed, "SAVE"));
        Selenide.switchTo().defaultContent();
    }

    private NewSubAccountRequest newSubAccountRequestPage;

    public SearchOrganisation(WebDriver driver) {
        super(driver);
        setNewSubAccountRequestPage(new NewSubAccountRequest(lDriver));
    }
}
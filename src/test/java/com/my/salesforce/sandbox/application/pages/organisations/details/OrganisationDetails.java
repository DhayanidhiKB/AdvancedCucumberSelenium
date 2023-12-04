package com.my.salesforce.sandbox.application.pages.organisations.details;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.details.OrganisationDetailsElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

@Getter
@Setter
public class OrganisationDetails extends BasePage implements OrganisationDetailsElements {
    public OrganisationDetails is_ready(String org_name, String abn) {
        shouldHaveText(LegalEntityName, org_name);
        shouldHaveText(ABN, abn);
        isItVisible(RecordType);
        isItVisible(EditLegalEntityName);
        return this;
    }

    public OrganisationDetails related() {
        clickOn(Related);
        return this;
    }

    public OrganisationDetails related_billing_accounts() {
        isItInteractable(ABN).scrollIntoView(true);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        return this;
    }

    public OrganisationDetails related_sub_account_requests() {
        isItInteractable(ABN).scrollIntoView(true);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        return this;
    }

    public void support_requests() {
        clickOn(DealSupportRequests);
    }

    private CreditLimit creditLimit;
    private OrganisationRelatedLinks organisationRelatedLinks;
    private Related related;

    public OrganisationDetails(WebDriver driver) {
        super(driver);
        setCreditLimit(new CreditLimit(lDriver));
        setOrganisationRelatedLinks(new OrganisationRelatedLinks(lDriver));
        setRelated(new Related(lDriver));
    }
}
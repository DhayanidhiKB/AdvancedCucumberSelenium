package com.my.salesforce.sandbox.application.pages.organisations.details;

import com.codeborne.selenide.SelenideElement;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.details.OrganisationRelatedLinksElements;
import com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.BillingAccounts;
import com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.SubAccountRequests;
import com.my.salesforce.sandbox.application.pages.organisations.contacts.Contacts;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class OrganisationRelatedLinks extends BasePage implements OrganisationRelatedLinksElements {
    public OrganisationRelatedLinks contacts() {
        clickOn(Contacts);
        return this;
    }

    public OrganisationRelatedLinks billing_accounts() {
        click_on(BillingAccount);
        return this;
    }

    public OrganisationRelatedLinks sub_account_requests() {
        click_on(SubAccountRequests);
        return this;
    }

    public void click_on(@NotNull SelenideElement element) {
        if (!element.isDisplayed()) {
            executeJavaScript("window.scrollBy(0,250)");
            clickOn(ShowAll);
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
            executeJavaScript("window.scrollBy(0,750)");
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
            if (!element.isDisplayed()) {
                executeJavaScript("window.scrollBy(0,250)");
                Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
            }
        }
        clickOn(element);
    }

    private Contacts contacts;
    private BillingAccounts billingAccountsPage;
    private com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.SubAccountRequests subAccountRequestsPage;

    public OrganisationRelatedLinks(WebDriver driver) {
        super(driver);
        setContacts(new Contacts(lDriver));
        setBillingAccountsPage(new BillingAccounts(lDriver));
        setSubAccountRequestsPage(new SubAccountRequests(lDriver));
    }
}
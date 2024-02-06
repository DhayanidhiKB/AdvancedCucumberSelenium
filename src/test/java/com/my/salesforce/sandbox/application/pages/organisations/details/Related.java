package com.my.salesforce.sandbox.application.pages.organisations.details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.details.RelatedElements;
import com.my.salesforce.sandbox.application.pages.organisations.contacts.Contacts;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class Related extends BasePage implements RelatedElements {
    public Related is_ready() {
        isItInteractable(Roles);
        return this;
    }

    public Related contacts() {
        Roles.scrollIntoView(true);
        isItInteractable(Assets).scrollIntoView(true);
        return this;
    }

    private Contacts contacts;

    public Related(WebDriver driver) {
        super(driver);
        setContacts(new Contacts(lDriver));
    }
}
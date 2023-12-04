package com.my.salesforce.sandbox.application.pages.organisations.contacts;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.contacts.ContactsElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class Contacts extends BasePage implements ContactsElements {
    public Contacts is_ready() {
        isItInteractable(Contacts);
        isItInteractable(MergeContacts);
        return this;
    }

    public Contacts new_contact(String title, String first_name, String last_name, String email) {
        clickOn(NewContact);
        getNewContactPage().is_ready().save().validate_error()
                .fill(title, first_name, last_name, email).save();
        return this;
    }

    public void verify_contact(String contact, String email) {
        isItInteractable(locate(ContactLink, contact));
        isItInteractable(locate(Email, email));
    }

    private NewContact newContactPage;

    public Contacts(WebDriver driver) {
        super(driver);
        setNewContactPage(new NewContact(lDriver));
    }
}
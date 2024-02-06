package com.my.salesforce.sandbox.application.pages.proposal;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.proposal.EmailPageElements;
import org.openqa.selenium.WebDriver;

public class EmailPage extends BasePage implements EmailPageElements {
    public EmailPage is_ready() {
        isItVisible(PageDescription);
        isItInteractable(LookUp);
        isItInteractable(CC);
        isItInteractable(locate(Options, "template"));
        isItInteractable(locate(Options, "attach"));
        isItInteractable(locate(Options, "preview"));
        isItInteractable(locate(Options, "cancel"));
        return this;
    }

    public void send_proposal_to(String contact) {
        setValue(contact, To);
        setValue("", AdditionalTo);
        clickOn(locate(Options, "send"));
        isItVisible(ConfirmationPanel);
        isItVisible(ConfirmationPanelText);
        clickOn(locate(WasProposalSent, "Yes"));
    }

    public EmailPage(WebDriver driver) {
        super(driver);
    }
}
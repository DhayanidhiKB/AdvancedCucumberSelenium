package com.my.salesforce.sandbox.application.pages.dsr.details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.details.DealSupportRequestDetailsPageElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class DSRHeader extends BasePage implements DealSupportRequestDetailsPageElements {
    public DSRHeader is_ready() {
        isItVisible(Header);
        return this;
    }

    public DSRHeader dsr_is(String stage) {
        isItInteractable(locate(CurrentStage, stage));
        return this;
    }

    public DSRHeader not_submitted() {
        isItVisible(locate(Message, "The Deal Support Request has not been submitted."));
        isItVisible(locate(Message, "Please click the ‘Submit’ button to ensure your request is received by the Support Team."));
        return this;
    }

    public DSRHeader submit_dsr() {
        clickOn(More);
        clickOn(locate(MoreLinks, "Submit"));
        isItVisible(WillBeSubmittedMessage);
        clickOn(locate(SubmitButton, "Save"));
        return this;
    }

    public DSRHeader submit_psr() {
        clickOn(locate(Link, "Submit"));
        isItVisible(WillBeSubmittedMessage);
        clickOn(locate(SubmitButton, "Save"));
        return this;
    }

    public void create_billing_account_rating_plan(String action) {
        clickOn(More);
        clickOn(locate(MoreLinks, action));
    }

    public void complete_deal_support_request(String status) {
        clickOn(More);
        clickOn(locate(MoreLinks, "Complete"));
        isItInteractable(locate(Complete, "Stage", status));
        isItInteractable(locate(Complete, "Status", status));
        clickOn(CompleteDSR);
        isItInteractable(locate(Edit, "Approved Event"));
    }

    private DealSupportRequestDetails dealSupportRequestDetailsPage;

    public DSRHeader(WebDriver driver) {
        super(driver);
        setDealSupportRequestDetailsPage(new DealSupportRequestDetails(lDriver));
    }
}
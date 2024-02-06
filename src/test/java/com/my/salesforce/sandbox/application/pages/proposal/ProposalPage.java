package com.my.salesforce.sandbox.application.pages.proposal;

import com.codeborne.selenide.Selenide;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.proposal.KeyFieldsElements;
import com.my.salesforce.sandbox.application.elements.proposal.ProposalPageElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class ProposalPage extends BasePage implements ProposalPageElements, KeyFieldsElements {
    public ProposalPage is_ready() {
        isItVisible(Header);
        isItVisible(Name);
        isItInteractable(locate(Action, "Edit"));
        isItInteractable(MoreActions);
        return this;
    }

    public ProposalPage verify_status(String status, String contact) {
        getKeyFields().is_ready(contact);
        isItInteractable(locate(ProposalStage, status));
        return this;
    }

    public ProposalPage create_proposal(String contact) {
        Selenide.refresh();
        is_ready().getKeyFields().is_ready(contact);
        getProposalActions().create(contact);
        is_ready().getKeyFields().is_ready(contact);
        return this;
    }

    public ProposalPage verify_stage(String stage) {
        is_ready();
        if (!(locateElements(ProposalStage, stage).size() > 0)) {
            getProposalActions().page_refresh_and_wait();
            if (!(locateElements(ProposalStage, stage).size() > 0)) {
                getProposalActions().page_refresh_and_wait();
            }
            is_ready();
        }
        isItInteractable(locate(ProposalStage, stage));
        return this;
    }

    public String getName() {
        return $(Name).getText();
    }

    public ProposalPage present_the_proposal(String documentName, String contact) {
        getProposalActions().present(documentName, contact).switch_to_default_content();
        getEmailPage().is_ready().send_proposal_to(contact);
        return this;
    }

    public ProposalPage accept_the_proposal() {
        getProposalActions().accept();
        return this;
    }

    private KeyFields keyFields;
    private EmailPage emailPage;
    private CreateContractTypes createContractTypes;
    private ProposalActions proposalActions;

    public ProposalPage(WebDriver driver) {
        super(driver);
        setKeyFields(new KeyFields(lDriver));
        setEmailPage(new EmailPage(lDriver));
        setCreateContractTypes(new CreateContractTypes(lDriver));
        setProposalActions(new ProposalActions(lDriver));
    }
}
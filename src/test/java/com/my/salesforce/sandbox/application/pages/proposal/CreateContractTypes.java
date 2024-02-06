package com.my.salesforce.sandbox.application.pages.proposal;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.proposal.ProposalPageElements;
import com.my.salesforce.sandbox.application.pages.contract.ConfirmContractDetails;
import com.my.salesforce.sandbox.application.pages.opportunities.creditassessment.CompleteCreditAssessment;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class CreateContractTypes extends BasePage implements ProposalPageElements {
    public CreateContractTypes create_contract(String current_status) {
        locate(ProposalStage, current_status).scrollIntoView(true);
        clickOn(CreateContract);
        return this;
    }

    public CreateContractTypes contract_amendment() {
        browser.refresh();
        isItInteractable(locate(ProposalStageAhead, "Denied")).scrollIntoView(true);
        clickOn(Amendment);
        return this;
    }

    public CreateContractTypes contract_renewal(String option) {
        browser.refresh();
        isItInteractable(locate(ProposalStageAhead, "Denied")).scrollIntoView(true);
        clickOn(locate(ProposalStep, option));
        return this;
    }

    private CompleteCreditAssessment completeCreditAssessment;
    private ConfirmContractDetails confirmContractDetails;

    public CreateContractTypes(WebDriver driver) {
        super(driver);
        setCompleteCreditAssessment(new CompleteCreditAssessment(lDriver));
        setConfirmContractDetails(new ConfirmContractDetails(lDriver));
    }
}
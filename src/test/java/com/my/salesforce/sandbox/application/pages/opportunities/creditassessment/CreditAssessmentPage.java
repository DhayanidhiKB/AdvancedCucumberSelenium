package com.my.salesforce.sandbox.application.pages.opportunities.creditassessment;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.creditassessment.CreditAssessmentPageElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class CreditAssessmentPage extends BasePage implements CreditAssessmentPageElements {
    public CreditAssessmentPage is_it_ready() {
        isItVisible(Header);
        isItInteractable(Reload);
        return this;
    }

    public void primary_proposal_is_required() {
        getOpcAgainstPrimaryProposal().is_required().hit_reload_after_opc();
    }

    public CreditAssessmentPage choose(String account_type) {
        clickOn(locate(Option, account_type));
        executeJavaScript("window.scrollBy(0,100)");
        clickOn(Next);
        return this;
    }

    public void verify_assessment_status() {
        isItVisible(AssessmentStatus);
        isItInteractable(AssessmentOutcome);
    }

    public void for_closed_opportunity() {
        isItVisible(ClosedOpportunity);
    }

    private OPCAgainstPrimaryProposal opcAgainstPrimaryProposal;
    private SearchOrganisation searchOrganisationPage;
    private MoveOrCreateNewCreditAssessment moveOrCreateNewCreditAssessment;

    public CreditAssessmentPage(WebDriver driver) {
        super(driver);
        setSearchOrganisationPage(new SearchOrganisation(lDriver));
        setOpcAgainstPrimaryProposal(new OPCAgainstPrimaryProposal(lDriver));
        setMoveOrCreateNewCreditAssessment(new MoveOrCreateNewCreditAssessment(lDriver));
    }
}
package com.my.salesforce.sandbox.application.pages.opportunities.creditassessment;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.creditassessment.CreditAssessmentMessages;
import org.openqa.selenium.WebDriver;

public class OPCAgainstPrimaryProposal extends BasePage implements CreditAssessmentMessages {
    public OPCAgainstPrimaryProposal is_required() {
        isItVisible(OPCRequired);
        return this;
    }

    public void hit_reload_after_opc() {
        isItVisible(AfterOPCHitReload);
    }

    public OPCAgainstPrimaryProposal(WebDriver driver) {
        super(driver);
    }
}
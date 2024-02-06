package com.my.salesforce.sandbox.application.pages.proposal;

import com.codeborne.selenide.Selenide;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.proposal.KeyFieldsElements;
import com.my.salesforce.sandbox.application.elements.proposal.ProposalPageElements;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class ProposalActions extends BasePage implements ProposalPageElements, KeyFieldsElements {
    public void refresh_and_wait() {
        page_refresh_and_wait();
        page_refresh_and_wait();
        page_refresh_and_wait();
    }

    public void create(String contact) {
        isItInteractable(locate(KeyContact, contact)).scrollIntoView(true);
        clickOn(locate(ProposalStep, "Create"));
    }

    public ProposalActions present(String documentName, String contact) {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        isItInteractable(locate(ProposalStage, "Generated"));
        isItInteractable(locate(KeyContact, contact)).scrollIntoView(true);
        clickOn(locate(ProposalStep, "Present"));
        switch_to_frame(iFrame);
        isItInteractable(CancelProposal);
        isItVisible(AttachementContentType);
        clickOn(locate(AttachementCheckBox, documentName));
        clickOn(ProceedWithProposal);
        return this;
    }

    public void switch_to_default_content() {
        Selenide.switchTo().defaultContent();
    }

    public void accept() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        isItInteractable(locate(ProposalStage, "Presented")).scrollIntoView(true);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        executeJavaScript("window.scrollBy(0,350)");
        clickOn(locate(ProposalStep, "Accept"));
    }

    public ProposalActions(WebDriver driver) {
        super(driver);
    }
}
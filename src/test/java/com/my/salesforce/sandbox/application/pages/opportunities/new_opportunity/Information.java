package com.my.salesforce.sandbox.application.pages.opportunities.new_opportunity;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity.InformationElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

@Getter
@Setter
public class Information extends BasePage implements InformationElements {
    public Information is_ready() {
        isItInteractable(OpportunitySubType);
        isItInteractable(Stage);
        isItInteractable(OpportunityName);
        isItInteractable(LegalEntityName);
        return this;
    }

    public Information choose_subType_as(String type) {
        mouseOverAndSelect(type, OpportunitySubType);
        return this;
    }

    public Information choose_stage_as(String stage) {
        isItInteractable(Stage);
        mouseOverAndSelect(stage, Stage);
        return this;
    }

    public Information enter_name_as(String opp_name) {
        setValue(opp_name, OpportunityName);
        return this;
    }

    public Information choose_next_step_as(String next_step) {
        isItInteractable(NextStep);
        mouseOverAndSelect(next_step, NextStep);
        return this;
    }

    public Information search_for_entity_name(String org_name) {
        Stage.scrollIntoView(true);
        type(org_name, LegalEntityName);
        clickOn(locate(SearchAllResultsFor, org_name));
        getLegalEntityName().is_ready().select(org_name);
        return this;
    }

    public Information search_for_key_contact(String first_name, String last_name) {
        clickOn(KeyContact);
        type(first_name + " " + last_name, KeyContact);
        clickOn(locate(SearchAllResultsFor, first_name + " " + last_name));
        getKeyContact().is_ready().select(first_name + " " + last_name);
        return this;
    }

    public Information close_date(String date) {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        setValue(date, CloseDate);
        return this;
    }

    private LegalEntityName legalEntityName;
    private KeyContact keyContact;
    private StarTrackDetails starTrackDetails;

    public Information(WebDriver driver) {
        super(driver);
        setLegalEntityName(new LegalEntityName(lDriver));
        setKeyContact(new KeyContact(lDriver));
        setStarTrackDetails(new StarTrackDetails(lDriver));
    }
}
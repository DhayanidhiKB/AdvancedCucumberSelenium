package com.my.salesforce.sandbox.application.pages.opportunities.details.headers;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.details.sub_header.OpportunitySubHeaderElements;
import com.my.salesforce.sandbox.application.pages.opportunities.details.Activity;
import com.my.salesforce.sandbox.application.pages.opportunities.details.OpportunityTabSet;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class OpportunitySubHeader extends BasePage implements OpportunitySubHeaderElements {
    public OpportunitySubHeader is_ready() {
        isItVisible(OutstandingActions);
        isItInteractable(Refresh);
        return this;
    }

    public OpportunitySubHeader verify_outstanding_actions(@NotNull String stage) {
        isItInteractable(locate(CurrentStage, stage));
        getOutStandingActions().is_ready().verify_actions_for_the_stage(stage);
        return this;
    }

    public OpportunitySubHeader activity(String subject, String contact) {
        clickOn(Activity);
        getActivity().is_ready().new_task(subject, contact).save().is_ready();
        isItInteractable(Activity);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        getOutStandingActions().can_progress();
        executeJavaScript("window.scrollBy(0,150)");
        return this;
    }

    public OpportunitySubHeader scroll_into_view() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        isItInteractable(locate(AheadStage, "Closed")).scrollIntoView(true);
        executeJavaScript("window.scrollBy(0,50)");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(2));
        return this;
    }

    public OpportunitySubHeader when_opportunity_is_closed(String stage_name) {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        isItInteractable(locate(CurrentStage, stage_name));
        executeJavaScript("window.scrollBy(0,50)");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(2));
        return this;
    }

    private OpportunityTabSet opportunityTabSet;
    private OutStandingActions outStandingActions;
    private Activity activity;


    public OpportunitySubHeader(WebDriver driver) {
        super(driver);
        setOpportunityTabSet(new OpportunityTabSet(lDriver));
        setOutStandingActions(new OutStandingActions(lDriver));
        setActivity(new Activity(lDriver));
    }
}
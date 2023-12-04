package com.my.salesforce.sandbox.application.pages.welcome;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.welcome.AppNavigatorElements;
import com.my.salesforce.sandbox.application.pages.opportunities.Opportunities;
import com.my.salesforce.sandbox.application.pages.organisations.Organisations;
import com.my.salesforce.sandbox.application.pages.proposal.ProposalPage;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class AppNavigator extends BasePage implements AppNavigatorElements {
    public AppNavigator is_ready() {
        isItInteractable(AppNavigationContainer);
        return this;
    }

    public AppNavigator set(String app) {
        clickOn(OneAppLauncher);
        setValue(app, SearchApps);
        clickOn(locate(App, app));
        isItVisible(locate(AppName, app));
        return this;
    }

    public AppNavigator organisations() {
        actions().pause(Duration.ofSeconds(3)).perform();
        clickOn(Organisations);
        return this;
    }

    public AppNavigator opportunities() {
        actions().pause(Duration.ofSeconds(3)).perform();
        clickOn(Opportunities);
        return this;
    }

    private Organisations organisations;
    private Opportunities opportunities;
    private ProposalPage proposalPage;

    public AppNavigator(WebDriver driver) {
        super(driver);
        setOrganisations(new Organisations(lDriver));
        setOpportunities(new Opportunities(lDriver));
        setProposalPage(new ProposalPage(lDriver));
    }
}
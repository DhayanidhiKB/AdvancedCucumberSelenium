package com.my.salesforce.sandbox.application.pages.opportunities.related;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.related.RelatedElements;
import com.my.salesforce.sandbox.application.pages.opportunities.related.csq.CSQQuestionnaire;
import com.my.salesforce.sandbox.application.pages.opportunities.related.csq.NewCSQ;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class Related extends BasePage implements RelatedElements {
    public Related is_it_ready() {
        isItVisible(Header);
        return this;
    }

    public void new_competitor(String type, String product, String name,
                               String status, String advantage) {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        executeJavaScript("window.scrollBy(0,250)");
        clickOn(NewCompetitor);
        getNewCompetitorAndIncumbent().is_ready().enter(type, product, name, status, advantage).save();
        isItInteractable(NewCompetitor);
        clickOn(NewCompetitorTable);
    }

    public Related new_csq() {
        executeJavaScript("window.scrollBy(0,150)");
        isItInteractable(NewCompetitor);
        clickOn(NewCSQ);
        return this;
    }

    public Related open_csq() {
        isItInteractable(NewCSQ);
        executeJavaScript("window.scrollBy(0,75)");
        clickOn(CSQLink);
        return this;
    }

    private NewCompetitorAndIncumbent newCompetitorAndIncumbent;
    private com.my.salesforce.sandbox.application.pages.opportunities.related.csq.NewCSQ newCSQPage;
    private CSQQuestionnaire csqQuestionnairePage;

    public Related(WebDriver driver) {
        super(driver);
        setNewCompetitorAndIncumbent(new NewCompetitorAndIncumbent(lDriver));
        setNewCSQPage(new NewCSQ(lDriver));
        setCsqQuestionnairePage(new CSQQuestionnaire(lDriver));
    }
}
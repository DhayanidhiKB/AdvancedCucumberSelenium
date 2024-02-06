package com.my.salesforce.sandbox.application.pages.opportunities.related;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.related.NewCompetitorAndIncumbentElements;
import org.openqa.selenium.WebDriver;

public class NewCompetitorAndIncumbent extends BasePage implements NewCompetitorAndIncumbentElements {
    public NewCompetitorAndIncumbent is_ready() {
        isItVisible(Header);
        isItInteractable(CompetitorType);
        isItInteractable(Save);
        return this;
    }

    public NewCompetitorAndIncumbent enter(String type, String product, String name,
                                           String status, String advantage) {
        mouseOverAndSelect(type, CompetitorType);
        isItInteractable(Product);
        mouseOverAndSelect(product, Product);
        isItInteractable(CompetitorName);
        mouseOverAndSelect(name, CompetitorName);
        isItInteractable(CompetitiveStatus);
        mouseOverAndSelect(status, CompetitiveStatus);
        setValue(advantage, CompetitiveAdvantage);
        return this;
    }

    public void save() {
        clickOn(Save);
    }

    public NewCompetitorAndIncumbent(WebDriver driver) {
        super(driver);
    }
}
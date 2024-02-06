package com.my.salesforce.sandbox.application.pages.opportunities.details;

import com.codeborne.selenide.Selenide;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.details.OpportunityDetailsElements;
import com.my.salesforce.sandbox.application.pages.shopping_cart.AllProducts;
import lombok.*;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class OpportunityDetails extends BasePage implements OpportunityDetailsElements {
    public OpportunityDetails is_ready() {
        isItInteractable(locate(Edit, "Stage"));
        return this;
    }

    public OpportunityDetails change_stage_to(String stage, String next_step) {
        mouseOverAndClickOn(locate(Edit, "Stage"));
        executeJavaScript("window.scrollBy(0,125)");
        isItInteractable(Stage);
        mouseOverAndSelect(stage, Stage);
        executeJavaScript("window.scrollBy(0,50)");
        isItInteractable(NextStep);
        mouseOverAndSelect(next_step, NextStep);
        return this;
    }

    public OpportunityDetails add(String field, String text) {
        isItInteractable(RevenueHeader).scrollIntoView(true);
        setValue(text, locate(TextArea, field));
        return this;
    }

    public void save_changes() {
        clickOn(locate(Action, "Save"));
        isItInteractable(locate(Edit, "Stage"));
        isItInteractable(locate(Edit, "Next Step"));
        Selenide.refresh();
    }

    private AllProducts allProducts;

    public OpportunityDetails(WebDriver driver) {
        super(driver);
        setAllProducts(new AllProducts(lDriver));
    }
}
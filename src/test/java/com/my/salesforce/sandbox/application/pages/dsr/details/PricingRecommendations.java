package com.my.salesforce.sandbox.application.pages.dsr.details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.details.DealSupportRequestDetailsPageElements;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PricingRecommendations extends BasePage implements DealSupportRequestDetailsPageElements {
    public void edit(String quoteID, String recommendation, String pricingStructure,
                     String tier, String endDate, String approvedEvent,
                     String evaluated_spend) {
        locate(Edit, "Estimated annual volume").scrollIntoView(true);
        clickOn(locate(Edit, "Compass Quote ID"));
        setValue(quoteID, locate(Input, "Compass Quote ID"));
        setValue(recommendation, locate(TextArea, "Further Advice on Pricing Recommendation"));
        clickOn(locate(ListBox, "Approved pricing structure"));
        clickOn(locate(ListMenus, pricingStructure));
        executeJavaScript("window.scrollBy(0,100)");
        clickOn(locate(ListBox, "Approved tier"));
        isItInteractable(locate(ListMenus, tier).shouldBe(visible)).scrollIntoView(true).click();
        setValue(endDate, locate(Date, "Quote Validity End Date"));
        clickOn(locate(ListBox, "Approved Event"));
        clickOn(locate(ListMenus, approvedEvent));
        setValue(evaluated_spend, locate(Input, "Evaluated Spend (per annum)"));
        clickOn(Save);
        isItInteractable(locate(Edit, "Approved Event"));
    }

    public PricingRecommendations(WebDriver driver) {
        super(driver);
    }
}
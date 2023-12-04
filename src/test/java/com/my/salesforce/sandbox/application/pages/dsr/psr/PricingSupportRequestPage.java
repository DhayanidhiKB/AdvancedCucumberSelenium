package com.my.salesforce.sandbox.application.pages.dsr.psr;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.PricingSupportRequestPageElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class PricingSupportRequestPage extends BasePage implements PricingSupportRequestPageElements {
    public PricingSupportRequestPage is_ready() {
        isItVisible(Header);
        isItVisible(locate(Label, "Legal Entity Name"));
        isItInteractable(locate(SearchBox, "Name of Product Specialist"));
        isItInteractable(locate(ListBox, "Agreement Duration"));
        isItInteractable(locate(Button, "Cancel"));
        isItInteractable(locate(Button, "Save & New"));
        isItInteractable(locate(Button, "Save"));
        return this;
    }

    public void save_psr(String agreementDuration, String agreementType,
                         String transit_cover_type, String transit_cover_amount,
                         String annualRevenue, String annualVolume, String catalyst,
                         String salesJustification) {
        getAgreement().set(agreementDuration, agreementType);
        locate(SearchBox, "Primary Campaign").scrollIntoView(true);
        getSpeed().enable_returns().choose();
        $(Returns).scrollIntoView(true);
        getFeatures().choose();
        getSurcharges().choose();
        locate(DualListBox, "Features", "Wine and Alcohol").scrollIntoView(true);
        getSpeed().set_transit_cover(transit_cover_type, transit_cover_amount);
        locate(ListBox, "Transit Cover Type").scrollIntoView(true);
        getReasonsForRequest().enter(annualRevenue, annualVolume, catalyst, salesJustification);
        clickOn(locate(Button, "Save"));
    }

    private Agreement agreement;
    private Speed speed;
    private Features features;
    private Surcharges surcharges;
    private ReasonsForRequest reasonsForRequest;

    public PricingSupportRequestPage(WebDriver driver) {
        super(driver);
        setAgreement(new Agreement(lDriver));
        setSpeed(new Speed(lDriver));
        setFeatures(new Features(lDriver));
        setSurcharges(new Surcharges(lDriver));
        setReasonsForRequest(new ReasonsForRequest(lDriver));
    }
}
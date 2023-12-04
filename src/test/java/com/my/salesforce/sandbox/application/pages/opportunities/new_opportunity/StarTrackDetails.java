package com.my.salesforce.sandbox.application.pages.opportunities.new_opportunity;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity.InformationElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

@Getter
@Setter
public class StarTrackDetails extends BasePage implements InformationElements {
    public StarTrackDetails is_ready() {
        ClosedComments.scrollIntoView(true);
        isItInteractable(IsItStarTrack);
        isItInteractable(PostcodeMapping);
        isItInteractable(MinimumMonthlySpend);
        return this;
    }

    public void fill(String is_it_startrack, String postcode, String minimum_monthly_spend) {
        mouseOverAndSelect(is_it_startrack, IsItStarTrack);
        if (is_it_startrack.equals("Yes")) {
            clickOn(PostcodeMapping);
            setValue(postcode, PostcodeMapping);
            clickOn(locate(SearchAllResultsFor, postcode));
            getStarTrackPrimaryPickup().is_it_ready().select(postcode);
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(2));
            setValue(minimum_monthly_spend, MinimumMonthlySpend);
        }
    }

    private StarTrackPrimaryPickup starTrackPrimaryPickup;

    public StarTrackDetails(WebDriver driver) {
        super(driver);
        setStarTrackPrimaryPickup(new StarTrackPrimaryPickup(lDriver));
    }
}
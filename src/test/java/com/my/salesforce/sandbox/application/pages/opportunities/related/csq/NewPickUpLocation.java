package com.my.salesforce.sandbox.application.pages.opportunities.related.csq;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.related.csq.NewPickUpLocationElements;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class NewPickUpLocation extends BasePage implements NewPickUpLocationElements {
    public NewPickUpLocation is_it_ready() {
        isItVisible(Header);
        isItInteractable(locate(Search, "Freight Offerings..."));
        isItInteractable(locate(Search, "for address"));
        return this;
    }

    public void new_pickup_location(String freight_offering, String address_line1, String address_line2,
                                    String suburb, String state, String postcode) {
        actions().moveToElement(locate(Search, "Freight Offerings..."))
                .sendKeys(locate(Search, "Freight Offerings..."), freight_offering)
                .pause(Duration.ofSeconds(1)).sendKeys(Keys.ARROW_DOWN)
                .pause(Duration.ofSeconds(1)).sendKeys(Keys.ARROW_DOWN)
                .pause(Duration.ofSeconds(1)).sendKeys(Keys.ENTER).build().perform();
        isItVisible(ApprovalStatus).scrollIntoView(true);
        actions().sendKeys(locate(Search, "for address"), address_line1)
                .pause(Duration.ofSeconds(1)).build().perform();
        actions().moveToElement($(OverrideAddress)).click()
                .pause(Duration.ofSeconds(1)).build().perform();
        setValue(address_line1, locate(Input, "Address Line 1"));
        setValue(address_line2, locate(Input, "Address Line 2"));
        setValue(suburb, locate(Input, "Suburb"));
        setValue(state, locate(Input, "State"));
        setValue(postcode, locate(Input, "Postcode"));
        isItVisible(LocationType).scrollIntoView(true).click();
        clickOn(locate(Option, "Metro"));
        locate(Input, "Postcode").scrollIntoView(true);
        clickOn(Save);
    }

    public NewPickUpLocation(WebDriver driver) {
        super(driver);
    }
}
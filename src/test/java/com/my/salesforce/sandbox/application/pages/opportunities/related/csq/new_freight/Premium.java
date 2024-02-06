package com.my.salesforce.sandbox.application.pages.opportunities.related.csq.new_freight;

import com.my.salesforce.sandbox.Constants;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.related.csq.new_freight.PremiumElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class Premium extends BasePage implements PremiumElements {
    public Premium is_ready() {
        isItVisible(Header);
        return this;
    }

    public Premium information() {
        isItVisible(Questionnaire);
        return this;
    }

    public void new_freight_offering() {
        getCartons().cartons_details(Constants.CARTONS_B2B, Constants.CARTONS_B2C,
                Constants.CARTONS_ITEMS, Constants.CARTONS_CONNOTES);
        getLocationType().location_type(Constants.LOCATION_TYPE);
        getPickupDetails().pickup_details(Constants.SCHEDULING, Constants.DAY);
        setValue(Constants.INTERSTATE_METRO, locate(Input,
                "Distribution Network - Interstate metro%", "1"));
        setValue(Constants.INTERSTATE_REGION, locate(Input,
                "Distribution Network-Interstate region%", "1"));
        setValue(Constants.INTRASTATE_METRO, locate(Input,
                "Distribution Network-Intrastate metro%", "1"));
        setValue(Constants.INTRASTATE_REGION, locate(Input,
                "Distribution Network-Intrastate region%", "1"));
        getTimeDetails().time_details(Constants.CURRENT_ARRIVAL_TIME, Constants.REQUIRED_ARRIVAL_TIME,
                Constants.CLOSING_TIME, Constants.READY_TIME, Constants.LOADING_TIME);
        clickOn(Save);
    }

    private Cartons cartons;
    private LocationType locationType;
    private PickupDetails pickupDetails;
    private TimeDetails timeDetails;

    public Premium(WebDriver driver) {
        super(driver);
        setCartons(new Cartons(lDriver));
        setLocationType(new LocationType(lDriver));
        setPickupDetails(new PickupDetails(lDriver));
        setTimeDetails(new TimeDetails(lDriver));
    }
}
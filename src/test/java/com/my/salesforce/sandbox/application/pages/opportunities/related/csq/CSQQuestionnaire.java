package com.my.salesforce.sandbox.application.pages.opportunities.related.csq;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.related.csq.CSQQuestionnairePageElements;
import com.my.salesforce.sandbox.application.pages.opportunities.related.csq.new_freight.NewFreightOffering;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class CSQQuestionnaire extends BasePage implements CSQQuestionnairePageElements {
    public CSQQuestionnaire is_it_ready() {
        isItVisible(Header);
        isItInteractable(locate(Links, "Freight Offerings"));
        isItInteractable(locate(Links, "Pick-Up Locations"));
        return this;
    }

    public CSQQuestionnaire new_freight_offering(String record_type) {
        clickOn(NewFreightOfferings);
        getNewFreightOffering().is_it_ready().choose_record_type(record_type)
                .getPremium().is_ready().information().new_freight_offering();
        return this;
    }

    public String get_freight_offering_name() {
        return FreightOfferingName.getText();
    }

    public void new_pickup_location(String freight_offering_name, String address_line1,
                                    String address_line2, String suburb, String state,
                                    String postcode) {
        clickOn(locate(Action, "New Pickup Location"));
        getNewPickUpLocation().is_it_ready().new_pickup_location(freight_offering_name, address_line1,
                address_line2, suburb, state, postcode);
        getNewPickUpLocationDetails().is_it_ready();
    }

    private NewFreightOffering newFreightOffering;
    private NewPickUpLocation newPickUpLocation;
    private NewPickUpLocationDetails newPickUpLocationDetails;

    public CSQQuestionnaire(WebDriver driver) {
        super(driver);
        setNewFreightOffering(new NewFreightOffering(lDriver));
        setNewPickUpLocation(new NewPickUpLocation(lDriver));
        setNewPickUpLocationDetails(new NewPickUpLocationDetails(lDriver));
    }
}
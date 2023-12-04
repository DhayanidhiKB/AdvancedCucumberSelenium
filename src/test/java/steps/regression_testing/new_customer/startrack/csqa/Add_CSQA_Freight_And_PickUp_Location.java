package steps.regression_testing.new_customer.startrack.csqa;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import pojos.PickUpLocation;
import steps.Base;

public class Add_CSQA_Freight_And_PickUp_Location {
    private final Base lBase;

    public Add_CSQA_Freight_And_PickUp_Location(@NotNull Base base) {
        this.lBase = base;
    }

    @When("Sales user creates csq related to {string} with freight offering of type[{string}] and pickup location")
    public void add_csqa_freight_and_pickup_location(String opp_name, String freight_type, @NotNull PickUpLocation pickUpLocation) {
        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();

        this.lBase.salesforce.visit(UserConfig.getProperties().appUrl())
                .getLoginPage()
                .is_ready().login_as(UserConfig.getProperties().salesUsername(),
                        Utilities.decode(UserConfig.getProperties().salesPassword()))
                .getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator().is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name)
                .getOpportunitySubHeader()
                .is_ready().scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().related()
                .getRelated()
                .is_it_ready().new_csq()
                .getNewCSQPage()
                .is_it_ready().save_csq(pickUpLocation.getCustomer_brief());

        String freight_offering = this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                .getRelated()
                .open_csq()
                .getCsqQuestionnairePage()
                .is_it_ready().new_freight_offering(freight_type)
                .is_it_ready().get_freight_offering_name();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader().getOpportunitySubHeader().getOpportunityTabSet()
                .getRelated()
                .getCsqQuestionnairePage()
                .new_pickup_location(freight_offering, pickUpLocation.getAddress_line1(),
                        pickUpLocation.getAddress_line2(), pickUpLocation.getSuburb(),
                        pickUpLocation.getState(), pickUpLocation.getPostcode());

        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();
    }
}
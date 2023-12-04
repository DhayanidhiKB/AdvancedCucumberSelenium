package steps.regression_testing;

import io.cucumber.java.en.Then;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Verify_Error_In_Edit_Products_Page {
    private final Base lBase;

    public Verify_Error_In_Edit_Products_Page(@NotNull Base base) {
        this.lBase = base;
    }

    @Then("Modify quantity {string}, revenue start date {string} and revenue end date {string}")
    public void product_quantity(String quantity, String start_date, String end_date) {
        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .getAllProducts().getEParcelProductAttributesPage().getCartActions()
                .getEditProducts()
                .edit_quantity(quantity).edit_start_date(start_date)
                .edit_end_date(end_date).save();
    }
}
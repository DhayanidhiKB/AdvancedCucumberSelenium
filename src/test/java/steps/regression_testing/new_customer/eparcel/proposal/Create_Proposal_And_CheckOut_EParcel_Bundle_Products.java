package steps.regression_testing.new_customer.eparcel.proposal;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import steps.Base;

import java.util.List;

public class Create_Proposal_And_CheckOut_EParcel_Bundle_Products {
    private final Base lBase;

    private String opportunityName;

    public Create_Proposal_And_CheckOut_EParcel_Bundle_Products(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("From {string} {string} user adds {string}, {string} after validating {string}, {string}, {string}, {string}")
    public void proposal_and_checkout_eParcel_products(
            String opp_name, @NotNull String user, String product1, String product2, String category,
            String price_structure, String lodgement_code, String transit_cover) {
        /*this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();*/

        opportunityName = opp_name;

        String user_name = "";
        String password = "";
        switch (user) {
            case "Onboarding":
                user_name = UserConfig.getProperties().onBoardingUsername();
                password = Utilities.decode(UserConfig.getProperties().onBoardingPassword());
                break;
            case "Sales":
                user_name = UserConfig.getProperties().salesUsername();
                password = Utilities.decode(UserConfig.getProperties().salesPassword());
                break;
        }

        this.lBase.salesforce.visit(UserConfig.getProperties().appUrl())
                .getLoginPage()
                .is_ready().login_as(user_name, password)
                .getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).add_pricing_product()
                .getAllProducts()
                .is_ready().add_to_cart(product1).still_not_configured(product1)
                .configure(product1)
                .getEParcelProductAttributesPage()
                .is_ready().enter_and_validate(product1, category, price_structure, lodgement_code, transit_cover)
                .getCartActions()
                .add_more_products();

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities().getOpportunityHeader()
                .getAllProducts()
                .switch_to_frame().add_to_cart(product2).configure(product2)
                .getEParcelProductAttributesPage()
                .is_ready().enter_and_validate(product2, category, price_structure, lodgement_code, transit_cover)
                .review_cart();
    }

    @Given("Below Products are checked out using {string} option after adding lodgement point {string}")
    public void adding_lodgement_points(String check_out_option, String lodgement_point, @NotNull DataTable details) {
        List<List<String>> products = details.asLists(String.class);

        for (List<String> product : products) {
            System.out.println(product.get(0));
            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                    .getOpportunities().getOpportunityHeader()
                    .getAllProducts()
                    .getEParcelProductAttributesPage()
                    .add_lodgementPoint(product.get(0), lodgement_point);
        }
        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .getAllProducts()
                .getEParcelProductAttributesPage()
                .refresh_pricing()
                .getCartActions()
                .check_out()
                .getCheckOut()
                .is_ready().select(check_out_option);

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getOpportunities()
                .getOpportunityHeader()
                .is_ready(opportunityName).verify_opportunity_actions();
    }
}
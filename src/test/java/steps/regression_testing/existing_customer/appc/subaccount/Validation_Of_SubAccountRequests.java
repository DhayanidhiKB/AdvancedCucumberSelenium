package steps.regression_testing.existing_customer.appc.subaccount;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Then;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Validation_Of_SubAccountRequests {
    private final Base lBase;

    public Validation_Of_SubAccountRequests(@NotNull Base base) {
        this.lBase = base;
    }

    @Then("Verify whether sub-account request {string} has been created for customer [{string}, {string}] with contract rates {string}, parcel send login {string}, product {string} and status is {string}")
    public void verify_sub_account(String sub_account_name, String org_name, String abn, String add_contract_rates, String parcel_send_login, String product, String status) {
        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();

        this.lBase.salesforce.visit(UserConfig.getProperties().appUrl())
                .getLoginPage()
                .is_ready().login_as(UserConfig.getProperties().salesUsername(),
                        Utilities.decode(UserConfig.getProperties().salesPassword()))
                .getHeader()
                .is_ready()
                .getAppNavigator()
                .is_ready().set("AP Sales").is_ready().organisations()
                .getOrganisations()
                .is_ready().search_for(org_name)
                .getOrganisationDetails()
                .is_ready(org_name, abn).related_sub_account_requests()
                .getOrganisationRelatedLinks()
                .sub_account_requests()
                .getSubAccountRequestsPage()
                .is_ready().validate_sub_account_request(sub_account_name, add_contract_rates, parcel_send_login, product, status);
    }

    @Then("Verify whether sub-account request {string} has been created for customer [{string}, {string}] with contract rates {string}, parcel send login {string}, products[{string} and {string}] and status is {string}")
    public void verify_sub_account(String sub_account_name, String org_name, String abn, String add_contract_rates, String parcel_send_login, String product1, String product2, String status) {
        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();

        this.lBase.salesforce.visit(UserConfig.getProperties().appUrl())
                .getLoginPage()
                .is_ready().login_as(UserConfig.getProperties().salesUsername(),
                        Utilities.decode(UserConfig.getProperties().salesPassword()))
                .getHeader()
                .is_ready()
                .getAppNavigator()
                .is_ready().set("AP Sales").is_ready().organisations()
                .getOrganisations()
                .is_ready().search_for(org_name)
                .getOrganisationDetails()
                .is_ready(org_name, abn).related_sub_account_requests()
                .getOrganisationRelatedLinks()
                .sub_account_requests()
                .getSubAccountRequestsPage()
                .is_ready().validate_sub_account_request(sub_account_name, add_contract_rates, parcel_send_login, product1 + ";" + product2, status);
    }
}
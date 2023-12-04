package steps.regression_testing.existing_customer.appc.subaccount;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class SubAccount_Request {
    private final Base lBase;

    public SubAccount_Request(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("From an existing customer [{string}, {string}], active billing account is identified")
    public void initiate_sub_account_request(String org_name, String abn) {
        this.lBase.billingAccountNumber =
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
                        .is_ready(org_name, abn).related_billing_accounts()
                        .getOrganisationRelatedLinks()
                        .billing_accounts()
                        .getBillingAccountsPage()
                        .is_ready().open_first_billing_account_details()
                        .getBillingAccountDetailsPage()
                        .is_ready().verify(org_name, abn).getAccountNumber();
        System.out.println("Billing Account Number:" + this.lBase.billingAccountNumber);
    }
}
package steps.regression_testing.existing_customer.appc.subaccount;

import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Multiple_SubAccount_Request {
    private final Base lBase;

    public Multiple_SubAccount_Request(@NotNull Base base) {
        this.lBase = base;
    }

    @When("User submits sub-account request for an existing {string} level APPC contract by populating name[{string}, {string}], contract rates {string}, parcel send {string} with {string}, {string} product , {string} eLMS, address[{string}, {string}, {string}, {string}, {string}] and lodgement point[{string}]")
    public void submit_sub_account_request(String contract_relationship, String sub_account_name, String sub_account_name2,
                                           String add_contract_rates, String parcel_send_login, String contact, String product, String eLMS,
                                           String address_line_1, String address_line_2, String sub_urb, String state,
                                           String post_code, String lodgement_point) {
        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOrganisations().getOrganisationDetails().getOrganisationRelatedLinks()
                .getBillingAccountsPage().getBillingAccountDetailsPage()
                .sub_account_request()
                .getNewSubAccountRequestPage()
                .is_ready()
                .getParentDetails()
                .verify_account_number(this.lBase.billingAccountNumber)
                .verify_source()
                .getRequestDetails()
                .is_ready().enter(sub_account_name, sub_account_name2)
                .set(add_contract_rates).scroll_to_parent_details()
                .choose(product)
                .set(parcel_send_login, contact).scroll_to_sub_account_name()
                .select(eLMS)
                .getAddressOfBusiness()
                .is_ready()
                .enter(address_line_1, address_line_2, sub_urb, state, post_code)
                .getOutletDetails()
                .is_ready().scroll_to_bottom().search_for(lodgement_point)
                .save_and_submit(contract_relationship);

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOrganisations().getOrganisationDetails().getOrganisationRelatedLinks()
                .getBillingAccountsPage().getBillingAccountDetailsPage()
                .is_ready();
    }
}
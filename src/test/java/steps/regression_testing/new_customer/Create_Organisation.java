package steps.regression_testing.new_customer;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Create_Organisation {
    private final Base lBase;

    public Create_Organisation(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("Onboarding user, created an organisation[{string}, {string}, {string}]" +
            " with credit limit {string}, billing address[{string}, {string}, {string}, {string}, {string}], " +
            "physical address [{string}, {string}, {string}, {string}, {string}] and " +
            "contact[{string}, {string}, {string}, {string}]")
    public void organisations(String org_name, String abn, String acn, String credit_limit,
                              String b_street, String b_city, String b_state, String b_code, String b_country,
                              String p_street, String p_city, String p_state, String p_code, String p_country,
                              String title, String first_name, String last_name, String email) {
        this.lBase.login_into_salesforce_as(UserConfig.getProperties().onBoardingUsername(),
                Utilities.decode(UserConfig.getProperties().onBoardingPassword()));

        this.lBase.salesforce.getLoginPage().getHeader()
                .getAppNavigator()
                .is_ready().set("AP Sales").is_ready()
                .organisations()
                .getOrganisations()
                .is_ready()
                .new_organisation(org_name, abn, acn, credit_limit,
                        b_street, b_city, b_state, b_code, b_country,
                        p_street, p_city, p_state, p_code, p_country)
                .getOrganisationDetails()
                .is_ready(org_name, abn)
                .related()
                .getRelated()
                .is_ready().contacts()
                .getContacts()
                .is_ready()
                .new_contact(title, first_name, last_name, email)
                .verify_contact(first_name + " " + last_name, email);

        this.lBase.log_off_from_salesforce();
    }
}
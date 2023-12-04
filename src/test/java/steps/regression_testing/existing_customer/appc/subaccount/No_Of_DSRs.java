package steps.regression_testing.existing_customer.appc.subaccount;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class No_Of_DSRs {
    private final Base lBase;

    public No_Of_DSRs(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("Got the number of DSRs from an existing customer [{string}, {string}]")
    public void no_of_dsr(String org_name, String abn) {
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
                .is_ready(org_name, abn).support_requests();

        this.lBase.noOfDSR = this.lBase.salesforce.getLoginPage()
                .getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .getOpportunitySubHeader().getOpportunityTabSet()
                .getSupportRequestsPage()
                .is_ready().get_noOf_support_requests();
        System.out.println("No Of DSRs: " + this.lBase.noOfDSR);

        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();
    }
}
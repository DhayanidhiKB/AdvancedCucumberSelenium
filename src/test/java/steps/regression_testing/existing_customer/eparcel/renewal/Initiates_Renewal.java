package steps.regression_testing.existing_customer.eparcel.renewal;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Initiates_Renewal {
    private final Base lBase;

    public Initiates_Renewal(@NotNull Base base) {
        this.lBase = base;
    }

    @When("Onboarding user opens the active contract from {string} initiates {string}")
    public void initiates_renewal(@NotNull String opp_name, String variation_category) {
        this.lBase.salesforce
                .visit(UserConfig.getProperties().appUrl())
                .getLoginPage()
                .is_ready()
                .login_as(UserConfig.getProperties().onBoardingUsername(),
                        Utilities.decode(UserConfig.getProperties().onBoardingPassword()))
                .getHeader()
                .is_ready()
                .getAppNavigator()
                .is_ready()
                .set("AP Sales")
                .is_ready();

        this.lBase.salesforce.getLoginPage().getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .is_ready().scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().products_contracts()
                .getProductsAndContracts()
                .is_ready().open_contract(0);

        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails()
                .getManageLodgementPointsAndAccountNumbersPage().getContractHeader()
                .getContractPage().getSteps()
                .initiate_renewal()
                .getRenewContract()
                .is_ready().renew(variation_category).back_to_shopping_cart();

        /*this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();*/
    }
}
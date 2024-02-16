package steps.regression_testing.existing_customer.eparcel.dov;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Initiates_DoV {
    private final Base lBase;

    public Initiates_DoV(@NotNull Base base) {
        this.lBase = base;
    }

    //Initiate DOV for Active Contract
    @When("Onboarding user opens the active contract from {string} initiates dov[{string},{string}]")
    public void initiates_dov(@NotNull String opp_name, String variation_category, String revenue_type) {

        try {
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
                    .getOpportunitySubHeader().getOpportunityTabSet()
                    .is_ready().products_contracts()
                    .getProductsAndContracts()
                    .is_ready().open_contract(0);

            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                    .getProposalPage().getCreateContractTypes()
                    .getConfirmContractDetails().getManageContractRelationshipsPage()
                    .getContractHeader()
                    .contract_is_active()
                    .getContractPage().getSteps()
                    .initiate_dov()
                    .getDovCategory()
                    .is_ready().enter(variation_category, revenue_type).back_to_shopping_cart();
        }
        catch(Exception e) {
            System.out.println("Execution failed because of following exception: "+e);
        }

    }
}
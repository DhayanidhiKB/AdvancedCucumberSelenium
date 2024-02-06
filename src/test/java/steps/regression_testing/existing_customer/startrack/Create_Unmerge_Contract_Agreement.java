package steps.regression_testing.existing_customer.startrack;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Create_Unmerge_Contract_Agreement {


    private final Base lBase;

    public Create_Unmerge_Contract_Agreement(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("Creates the contract [{int}] under {string}")

    public void create_the_contract(int index, String opp_name) {


        this.lBase.login_into_salesforce_as(UserConfig.getProperties().onBoardingUsername(),
                Utilities.decode(UserConfig.getProperties().onBoardingPassword()));

        this.lBase.salesforce.getLoginPage().getHeader()
                .search(opp_name)
                .getAppNavigator().is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().products_contracts()
                .getProductsAndContracts()
                .is_ready().open_contract(index);

        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails().getManageLodgementPointsAndAccountNumbersPage()
                .getContractHeader()
                .is_ready()
                .getContractPage()
                .create_contract().unmerge_document().create_contract_documents();

    }
}

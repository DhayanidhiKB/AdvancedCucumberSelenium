package steps.regression_testing.existing_customer.startrack;

import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import pojos.Contract;
import steps.Base;

public class Activate_The_Contract {
    private final Base lBase;

    public Activate_The_Contract(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("Contract documents created for {string} are made {string} and {string}")
    public void activate_the_contract(String opp_name, String contract_step, String contract_status, @NotNull Contract contract) {
        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader();

        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader();

        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader();
        System.out.println("Contract Name:" + this.lBase.contractName);
    }
}
package steps.regression_testing.new_customer.startrack.skip_proposal;

import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Create_Contract_Documents_By_Managing_Charge_Accounts {
    private final Base lBase;

    public Create_Contract_Documents_By_Managing_Charge_Accounts(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} contains {string} with contact {string} and creates a contract documents")
    public void create_contract_documents(String opp_name, String proposal_status, String sender) {
        this.lBase.salesforce
                .getLoginPage()
                .getHeader()
                .is_ready().search(this.lBase.proposalName)
                .getAppNavigator()
                .is_ready()
                .getProposalPage()
                .is_ready().verify_status(proposal_status, sender);

        this.lBase.salesforce.getLoginPage().getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().products_contracts()
                .getProductsAndContracts()
                .is_ready().open_contract(0);

        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails().getManageLodgementPointsAndAccountNumbersPage()
                .getContractHeader()
                .is_ready().page_refresh()
                .getContractPage()
                .create_contract()
                .create_contract_documents();

        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails().getManageLodgementPointsAndAccountNumbersPage()
                .getContractHeader()
                .is_ready().ready_to_sign();

        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();
    }
}
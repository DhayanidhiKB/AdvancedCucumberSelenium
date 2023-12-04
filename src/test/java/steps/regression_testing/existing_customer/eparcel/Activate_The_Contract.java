package steps.regression_testing.existing_customer.eparcel;

import io.cucumber.java.en.Given;
import org.jetbrains.annotations.NotNull;
import pojos.Contract;
import steps.Base;

public class Activate_The_Contract {
    private final Base lBase;

    public Activate_The_Contract(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("Contract documents created for {string} are made {string} and {string} after managing {string} lodgement point[{string}]")
    public void activate_the_contract(String opp_name, String contract_step, String contract_status, String product, @NotNull Contract contract) {
        this.lBase.salesforce
                .getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                /*getProposalPage()
                .is_ready()
                .getCreateContractTypes()
                .create_contract()
                .getContractHeader()
                .is_ready().verify_contract_details(opp_name, this.lBase.proposalName)
                .getContractPage()
                .create_contract()
                .getManageLodgementPointsAndAccountNumbersPage()
                .is_ready()
                .getManageAccountNumber()
                .manage_billing_account_for(product).back_to_contract()*/;

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                /*.getProposalPage().getCreateContractTypes()
                .getContractHeader()
                .is_ready()
                .getContractPage()
                .create_contract()
                .create_contract_documents()*/;

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                /*.getProposalPage().getCreateContractTypes()
                .getContractHeader()
                .is_ready().ready_to_sign()
                .getContractPage()
                .signing_the_contract(contract.getStep(), contract.getStep_status(), contract.getApproval_status())
                .verify(contract.getStep(), contract.getSigned_status())
                .activate_contract(contract_step, contract_status)
                .verify(contract_step, contract_status)*/;

        /*this.lBase.contractName = */this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                /*.getProposalPage().getCreateContractTypes()
                .getContractHeader()
                .get_name()*/;
        System.out.println("Contract Name:" + this.lBase.contractName);
    }
}
package steps.regression_testing.new_customer.appc.contract;

import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import pojos.ContractConfirmationDetails;
import steps.Base;

public class Applying_Contract_Relationship {
    private final Base lBase;

    public Applying_Contract_Relationship(@NotNull Base base) {
        this.lBase = base;
    }

    @When("Creates {string} contract from {string} proposal by confirming details like " +
            "condition[{string}], term[{string}], key contact[{string} {string}] and " +
            "relationship[{string}] applied at [{string}, {string}]")
    public void create_contract_and_apply_relationship(@NotNull String pricing, String proposal_status,
                                                       String condition, String contract_term, String first_name,
                                                       String last_name, String relationship, String level,
                                                       String lodgement_point, @NotNull ContractConfirmationDetails details) {
        this.lBase.salesforce
                .getLoginPage()
                .getHeader()
                .is_ready().search(this.lBase.proposalName)
                .getAppNavigator()
                .is_ready()
                .getProposalPage()
                .is_ready().verify_status(proposal_status, first_name + " " + last_name)
                .getCreateContractTypes()
                .create_contract(proposal_status)
                .getConfirmContractDetails()
                .is_ready().contract_entity_is(details.getEntity())
                .key_contact_is(first_name + " " + last_name)
                .contract_type_is(details.getType())
                .included_product(details.getProduct());

        if (pricing.equals("Customised") || condition.equals("Fixed Term")) {
            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                    .getProposalPage().getCreateContractTypes()
                    .getConfirmContractDetails()
                    .set_contract_condition_as(condition).enter_term(contract_term);
        }

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes()
                .getConfirmContractDetails()
                .next()
                .getManageContractRelationshipsPage()
                .is_ready().add(relationship, level).select_required(relationship, lodgement_point);

        if (!level.contains("Organisation")) {
            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                    .getProposalPage().getCreateContractTypes()
                    .getConfirmContractDetails()
                    .getManageContractRelationshipsPage()
                    .select_charge_account().select_sub_account();
        }

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes()
                .getConfirmContractDetails()
                .getManageContractRelationshipsPage()
                .apply_relationship().verify_added_billing_account(level).back_to_contract();

        if (pricing.equals("Customised")) {
            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                    .getProposalPage().getCreateContractTypes()
                    .getConfirmContractDetails().getManageContractRelationshipsPage()
                    .getContractHeader()
                    .is_ready()
                    .getContractPage()
                    .set(pricing);
        }

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes()
                .getConfirmContractDetails().getManageContractRelationshipsPage()
                .getContractHeader()
                .is_ready()
                .getContractPage()
                .create_contract();

        this.lBase.contractName = this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes()
                .getConfirmContractDetails().getManageContractRelationshipsPage()
                .getContractHeader()
                .is_ready().ready_to_sign().get_name();
        System.out.println("Contract Name:" + this.lBase.contractName);

        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
        this.lBase.salesforce.getLoginPage().is_ready();
    }
}
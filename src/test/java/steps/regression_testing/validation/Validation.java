package steps.regression_testing.validation;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.*;
import org.jetbrains.annotations.NotNull;
import pojos.DSR;
import pojos.Zones;
import steps.Base;

public class Validation {
    private final Base lBase;

    public Validation(@NotNull Base base) {
        this.lBase = base;
    }

    @Given("Logged in as Onboarding user")
    public void login_as_onboarding() {
        this.lBase.salesforce.visit(UserConfig.getProperties().appUrl())
                .getLoginPage()
                .is_ready().login_as(UserConfig.getProperties().onBoardingUsername(),
                        Utilities.decode(UserConfig.getProperties().onBoardingPassword()))
                .getHeader()
                .is_ready()
                .getAppNavigator()
                .is_ready().set("AP Sales").is_ready();
    }

    @Then("Make sure {string} {string} is updated with {string}#, {string}# and below details")
    public void open_dsr(String dsr_owner, String dsr_number, String billing_account_number,
                         String sub_account_number, @NotNull DSR dsr) {
        this.lBase.ratingTransactionID = this.lBase.salesforce.getLoginPage()
                .getHeader().is_ready().search(dsr_number)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .getNewDealSupportRequestPage()
                .getDsrHeader()
                .is_ready()
                .getDealSupportRequestDetailsPage()
                .verify(dsr_owner, dsr.getDsr_stage())
                .verify_integration_details(dsr.getIntegration_status(),
                        dsr.getRating_integration_status(),
                        dsr.getRating_integration_status_description());
    }

    @Then("In the contract page related to [{string},{string}] verify whether the relationship is at {string} level and {string} SAP contract related to {string}# with {string} is generated")
    public void contract_page(String opp_name, String proposal_name, String contract_relationship,
                              String sap_contract_status, String billing_account_number,
                              String lodgement_point) {
        this.lBase.salesforce.getLoginPage().getHeader()
                .is_ready().search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name)
                .getOpportunitySubHeader()
                .is_ready().scroll_into_view()
                .getOpportunityTabSet().is_ready().products_contracts()
                .getProductsAndContracts()
                .is_ready().open_contract(0);

        /*this.lBase.contractNumber = */
        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                /*.getProposalPage().getCreateContractTypes()
                .getContractHeader()
                .is_ready().verify_contract_details(opp_name, proposal_name)
                .getContractPage()
                .verify_contract_relationship(contract_relationship)
                .getContractRelatedLinks()
                .sap_contracts()
                .getSapContractsPage()
                .is_ready().get_contract_number_and_verify(sap_contract_status, billing_account_number, lodgement_point)*/;
    }

    @Then("Validate {string}, {string} in contract line item")
    public void contract_line_item(String tier, String price_structure, @NotNull Zones zones) {
        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                /*.getProposalPage().getCreateContractTypes()
                .getContractHeader()
                .is_ready()
                .getContractPage()
                .getContractRelatedLinks()
                .contract_lines()
                .getContractLineItemsPage()
                .is_ready()
                .verify_contract_line_item(tier, price_structure, zones.getZone(), zones.getZone1(), zones.getZone2())
                .is_ready().back_to_contract()*/;
    }

    @Then("Finally validate whether the Lodgement Point {string} is {string}")
    public void lodgement_point(String lodgement_point, String status, @NotNull DSR dsr) {
        /*this.lBase.ratingPlanID = */
        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                /*.getProposalPage().getCreateContractTypes()
                .getContractHeader()
                .is_ready()
                .getContractPage()
                .getContractRelatedLinks()
                .lodgement_points()
                .getLodgementPointsAndAccountNumberPage()
                .is_ready().get_rating_plan_id_and_verify(status, lodgement_point, dsr.getIntegration_status(), dsr.getIntegration_status_description())*/;

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                /*.getProposalPage().getCreateContractTypes()
                .getContractHeader()
                .getContractPage()
                .getContractRelatedLinks()
                .getLodgementPointsAndAccountNumberPage()
                .open(this.lBase.contractName)*/;

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                /*.getProposalPage().getCreateContractTypes()
                .getContractHeader()
                .is_ready()*/;
    }
}
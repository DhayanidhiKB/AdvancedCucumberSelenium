package steps.regression_testing.existing_customer.appc.skip_proposal;

import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import pojos.ContractConfirmationDetails;
import steps.Base;

public class Generate_Agreement_Document {
    private final Base lBase;

    public Generate_Agreement_Document(@NotNull Base base) {
        this.lBase = base;
    }

    @When("{string} user {string} for {string} redirected to contract page")
    public void generate_agreement_document(@NotNull String user,
                                            String check_out_option, String product,
                                            @NotNull ContractConfirmationDetails details) {
        /*String user_name = "";
        String password = "";
        switch (user) {
            case "Onboarding":
                user_name = UserConfig.getProperties().onBoardingUsername();
                password = Utilities.decode(UserConfig.getProperties().onBoardingPassword());
                break;
            case "Sales":
                user_name = UserConfig.getProperties().salesUsername();
                password = Utilities.decode(UserConfig.getProperties().salesPassword());
                break;
        }
*/
        /*this.lBase.proposalName = this.lBase.salesforce*//*.visit(UserConfig.getProperties().appUrl())*//*
                .getLoginPage()
                *//*.is_ready()
                .login_as(user_name, password)*//*
                .getHeader()
                *//*.is_ready().search(opp_name)*//*
                .getAppNavigator()
                *//*.is_ready()*//*
                .getOpportunities().getOpportunityHeader()
                *//*.is_ready(opp_name).verify_opportunity_actions()*//*
                .getOpportunitySubHeader()
                .is_ready().scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().products_contracts()
                .getProductsAndContracts()
                .is_ready().get_proposal_name();*/

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                .getOpportunities().getOpportunityHeader()
                .add_pricing_product()
                .getAllProducts()
                .is_ready().review();

        if (product.contains("Australia Post Parcel Contract")) {
            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                    .getOpportunities().getOpportunityHeader().getAllProducts()
                    .getAppcProductAttributesPage().getCheckOutAPPC()
                    .is_ready().check_out()
                    .choose(user, check_out_option).switch_to_default_content();
        } else if (product.contains("Premium")) {
            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                    .getOpportunities().getOpportunityHeader().getAllProducts()
                    .getStarTrackProductAttributesPage().getCartActions()
                    .check_out()
                    .getCheckOut()
                    .is_ready().no_of_options(user).select(check_out_option).switch_to_default_content()
                    .getEditProducts()
                    .is_ready().confirm_opportunity_products();
        } else {
            this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator()
                    .getOpportunities().getOpportunityHeader().getAllProducts()
                    .getEParcelProductAttributesPage().getCartActions()
                    .check_out()
                    .getCheckOut()
                    .is_ready().no_of_options(user).select(check_out_option).switch_to_default_content()
                    .getEditProducts()
                    .is_ready().confirm_opportunity_products();
        }

        this.lBase.salesforce.getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails()
                .is_ready().contract_entity_is(details.getEntity())
                .contract_type_is(details.getType())
                .included_product(details.getProduct()).next().
                getManageLodgementPointsAndAccountNumbersPage()
                .is_ready();

        if (details.getProduct().contains("eParcels")) {
            this.lBase.salesforce
                    .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                    .getCreateContractTypes().getConfirmContractDetails()
                    .getManageLodgementPointsAndAccountNumbersPage().getManageAccountNumber()
                    .manage_billing_account_for("eParcel").back_to_contract();
            this.lBase.click_on_eParcel_create_contract();

            this.lBase.salesforce
                    .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                    .getCreateContractTypes().getConfirmContractDetails()
                    .getManageLodgementPointsAndAccountNumbersPage()
                    .is_ready()
                    .getManageAccountNumber()
                    .manage_billing_account_for("eParcel Express").back_to_contract();
        }else if (details.getProduct().contains("International")) {
            this.lBase.salesforce
                    .getLoginPage().getHeader().getAppNavigator().getProposalPage()
                    .getCreateContractTypes().getConfirmContractDetails()
                    .getManageLodgementPointsAndAccountNumbersPage().getManageAccountNumber()
                    .manage_billing_account_for(product).back_to_contract();
            this.lBase.click_on_eParcel_create_contract();
        }


        this.lBase.salesforce.getLoginPage().getHeader().is_ready().sign_off();
    }
}
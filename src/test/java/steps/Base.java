package steps;

import com.my.salesforce.sandbox.application.pages.Salesforce;
import com.my.salesforce.sandbox.helpers.Utilities;
import com.my.salesforce.sandbox.properties.UserConfig;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

public class Base {
    public WebDriver lBrowser;
    public Salesforce salesforce;
    public String psrName;
    public String psrApprovedEvent;
    public String compassQuoteID;
    public String proposalName;
    public String contractName;
    public String ratingTransactionID;
    public String billingAccountNumber;
    public int noOfDSR;

    public String transactionID;

    public void login_into_salesforce_as(String userName, String passWord) {
        salesforce.visit(UserConfig.getProperties().appUrl())
                .getLoginPage()
                .is_ready()
                .login_as(userName, passWord)
                .getHeader()
                .is_ready();
    }

    public void login_into_salesforce_as(@NotNull String userType) {
        String user_name = "";
        String password = "";
        switch (userType) {
            case "Onboarding":
                user_name = UserConfig.getProperties().onBoardingUsername();
                password = Utilities.decode(UserConfig.getProperties().onBoardingPassword());
                break;
            case "Sales":
                user_name = UserConfig.getProperties().salesUsername();
                password = Utilities.decode(UserConfig.getProperties().salesPassword());
                break;
        }
        salesforce.visit(UserConfig.getProperties().appUrl())
                .getLoginPage()
                .is_ready().login_as(user_name, password)
                .getHeader()
                .is_ready();
    }

    public void click_on_eParcel_create_contract() {
        salesforce.getLoginPage().getHeader().getAppNavigator()
                .getProposalPage().getCreateContractTypes()
                .getConfirmContractDetails().getManageLodgementPointsAndAccountNumbersPage()
                .getContractHeader()
                .is_ready()
                .getContractPage()
                .create_contract();
    }

    public void eParcel_contract_is_ready_to_sign() {
        salesforce.getLoginPage().getHeader().getAppNavigator().getProposalPage()
                .getCreateContractTypes().getConfirmContractDetails().getManageLodgementPointsAndAccountNumbersPage()
                .getContractHeader()
                .is_ready().ready_to_sign();
    }

    public void log_off_from_salesforce() {
        salesforce.getLoginPage().getHeader().is_ready().sign_off();
        salesforce.getLoginPage().is_ready();
    }
}
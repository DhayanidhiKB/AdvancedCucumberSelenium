package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.new_subaccount.request_details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount.NewSubAccountRequestPageElements;
import com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.new_subaccount.AddressOfBusiness;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class RequestDetails extends BasePage implements NewSubAccountRequestPageElements {
    public RequestDetails is_ready() {
        clickOn(locate(SubHeader, "Sub Account Request Details"));
        return this;
    }

    public RequestDetails enter(String account_name1, String account_name2) {
        getSubAccountNames().enter_name_as(account_name1)
                .enter_name1_as(account_name2);
        return this;
    }

    public RequestDetails set(String contract_rates) {
        getContractRates().set(contract_rates);
        return this;
    }

    public RequestDetails scroll_to_parent_details() {
        locate(SubHeader, "Parent Details").scrollIntoView(true);
        return this;
    }

    public RequestDetails choose(String product) {
        getContractRates().choose(product);
        return this;
    }

    public RequestDetails set(String parcel_send, String sub_account_contract) {
        getParcelSend().set(parcel_send, sub_account_contract);
        return this;
    }

    public RequestDetails set_and_verify(String parcel_send, String sub_account_contract) {
        getParcelSend().set(parcel_send).verify(sub_account_contract);
        return this;
    }

    public RequestDetails scroll_to_sub_account_name() {
        locate(Enter, "Sub Account Name").scrollIntoView(true);
        return this;
    }

    public RequestDetails select(String eLMS) {
        getElms().set(eLMS);
        return this;
    }

    private SubAccountNames subAccountNames;
    private ContractRates contractRates;
    private ParcelSend parcelSend;
    private ELMS elms;
    private AddressOfBusiness addressOfBusiness;

    public RequestDetails(WebDriver driver) {
        super(driver);
        setSubAccountNames(new SubAccountNames(lDriver));
        setContractRates(new ContractRates(lDriver));
        setParcelSend(new ParcelSend(lDriver));
        setElms(new ELMS(lDriver));
        setAddressOfBusiness(new AddressOfBusiness(lDriver));
    }
}
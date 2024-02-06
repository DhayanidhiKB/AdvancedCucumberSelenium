package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.new_subaccount;

import com.codeborne.selenide.Selenide;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount.NewSubAccountRequestPageElements;
import com.my.salesforce.sandbox.helpers.Utilities;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class NewSubAccountRequest extends BasePage implements NewSubAccountRequestPageElements {
    public NewSubAccountRequest is_ready() {
        /*switch_to_frame(iFrame);*/
        isItVisible(Header);
        isItInteractable(locate(SubHeader, "Parent Details"));
        isItInteractable(locate(SubHeader, "Sub Account Request Details"));
        isItInteractable(locate(SubHeader, "Address of Business"));
        isItInteractable(locate(SubHeader, "Outlet Details"));
        return this;
    }

    public NewSubAccountRequest finalize(String proposal_name, String org_name, String sub_account_name,
                                         String sub_account_name2, String parcel_send, String sender, String elms,
                                         String address_line1, String address_line2, String sub_urb,
                                         String state, String post_code, String lodgement_point) {
        getParentDetails().verify(proposal_name, org_name)
                .getRequestDetails()
                .is_ready().enter(sub_account_name + "_" + Utilities.epochSeconds(),
                        sub_account_name2 + "_" + Utilities.epochSeconds())
                .set_and_verify(parcel_send, sender).scroll_to_sub_account_name().select(elms)
                .getAddressOfBusiness()
                .is_ready().enter(address_line1, address_line2, sub_urb, state, post_code)
                .getOutletDetails()
                .is_ready().scroll_to_bottom().search_for(lodgement_point)
                .save_and_finalize();
        Selenide.switchTo().defaultContent();
        return this;
    }

    private ParentDetails parentDetails;

    public NewSubAccountRequest(WebDriver driver) {
        super(driver);
        setParentDetails(new ParentDetails(lDriver));
    }
}
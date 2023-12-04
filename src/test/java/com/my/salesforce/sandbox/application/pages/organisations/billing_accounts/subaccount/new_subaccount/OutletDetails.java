package com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.new_subaccount;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.billing_accounts.subaccount.NewSubAccountRequestPageElements;
import com.my.salesforce.sandbox.application.pages.organisations.billing_accounts.subaccount.SubAccountList;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class OutletDetails extends BasePage implements NewSubAccountRequestPageElements {
    public OutletDetails is_ready() {
        clickOn(locate(SubHeader, "Outlet Details"));
        return this;
    }

    public OutletDetails scroll_to_bottom() {
        Confirmed.scrollIntoView(true);
        return this;
    }

    public OutletDetails search_for(String lodgement_point) {
        isItInteractable(SearchLodgementPoint);
        actions().sendKeys(SearchLodgementPoint, lodgement_point)
                .pause(Duration.ofSeconds(5)).build().perform();
        clickOn(locate(SearchOption, lodgement_point));
        return this;
    }

    public void save_and_submit(String contract_relationship) {
        isItInteractable(locate(Button, "Close")).scrollIntoView(true);
        clickOn(locate(Button, "Save as Draft"));
        getAlertWindow().handle_alert(contract_relationship);
        getSubAccountListPage().is_ready().submit_sub_account_request();
    }

    public void save_and_finalize() {
        isItInteractable(locate(Button, "Close")).scrollIntoView(true);
        clickOn(locate(Button, "Save as Draft"));
        getSubAccountListPage().is_ready().finalize_request();
    }

    private AlertWindow alertWindow;
    private SubAccountList subAccountListPage;

    public OutletDetails(WebDriver driver) {
        super(driver);
        setAlertWindow(new AlertWindow(lDriver));
        setSubAccountListPage(new SubAccountList(lDriver));
    }
}
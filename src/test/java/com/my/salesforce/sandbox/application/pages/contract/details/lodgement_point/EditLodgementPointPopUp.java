package com.my.salesforce.sandbox.application.pages.contract.details.lodgement_point;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.details.EditLodgementPointPopUpElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

public class EditLodgementPointPopUp extends BasePage implements EditLodgementPointPopUpElements {
    public EditLodgementPointPopUp is_ready() {
        isItVisible(Header);
        isItVisible(Pop);
        isItInteractable(locate(SearchOption, "Billing Account"));
        return this;
    }

    public EditLodgementPointPopUp search_charge_account() {
        clickOn(locate(SearchOption, "New Charge Account"));
        return this;
    }

    public EditLodgementPointPopUp search_billing_account() {
        clickOn(locate(SearchOption, "Billing Account"));
        return this;
    }

    public void switch_to(int noOfWindows, int windowIndex) {
        new WebDriverWait(lDriver, Duration.ofSeconds(60))
                .until(ExpectedConditions.numberOfWindowsToBe(noOfWindows));
        //Get handles of the windows
        Set<String> allWindowHandles = lDriver.getWindowHandles();
        ArrayList<String> handleList = new ArrayList<>(allWindowHandles);
        lDriver.switchTo().window(handleList.get(windowIndex));
    }

    public void select_charge_account() {
        switch_to(2, 1);
        clickOn(locate(Button, "Show All"));
        clickOn(Account);
        switch_to(1, 0);
        switch_to_frame(iFrame);
        clickOn(locate(Button, "Ok"));
    }

    public void select_billing_account() {
        switch_to(2, 1);
        clickOn(locate(Button, "Show all Billing Accounts for this Organisation"));
        clickOn(Account);
        switch_to(1, 0);
        switch_to_frame(iFrame);
        clickOn(locate(Button, "Ok"));
    }


    public EditLodgementPointPopUp(WebDriver driver) {
        super(driver);
    }
}
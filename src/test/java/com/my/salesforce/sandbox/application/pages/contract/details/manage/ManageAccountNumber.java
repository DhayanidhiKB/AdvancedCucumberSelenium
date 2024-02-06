package com.my.salesforce.sandbox.application.pages.contract.details.manage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.details.ManageLodgementPointsAndAccountNumbersPageElements;
import com.my.salesforce.sandbox.application.pages.contract.details.lodgement_point.EditLodgementPointPopUp;
import lombok.*;
import org.openqa.selenium.*;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class ManageAccountNumber extends BasePage implements ManageLodgementPointsAndAccountNumbersPageElements {
    public ManageAccountNumber manage_charge_account_for(String product) {
        clickOn($(By.xpath("//span[text()='" + product + "']/ancestor::td/preceding-sibling::td[3]//a")));
        getEditLodgementPointPopUp().is_ready().search_charge_account().select_charge_account();
        isItVisible($(By.xpath("//span[text()='" + product + "']/ancestor::td/preceding-sibling::td[2]//img[@title='Checked']")));
        return this;
    }

    public ManageAccountNumber manage_billing_account_for(String product) {
        clickOn($(By.xpath("//span[text()='" + product + "']/ancestor::td/preceding-sibling::td[3]//a")));
        getEditLodgementPointPopUp().is_ready().search_billing_account().select_billing_account();
        isItVisible($(By.xpath("//span[text()='" + product + "']/ancestor::td/preceding-sibling::td[2]//img[@title='Checked']")));
        return this;
    }

    public ManageAccountNumber manage_account_number() {
        ElementsCollection account_numbers = $$(ManageAccountNumbers);
        int count = account_numbers.size();
        if (count > 1) {
            for (WebElement account_number : account_numbers) {
                isItInteractable($(account_number));
                actions().pause(Duration.ofSeconds(5)).moveToElement($(account_number))
                        .pause(Duration.ofSeconds(1)).click().build().perform();
                getEditLodgementPointPopUp().is_ready().search_charge_account()
                        .select_charge_account();
            }
        } else {
            clickOn($(ManageAccountNumbers));
            getEditLodgementPointPopUp().is_ready().search_charge_account()
                    .select_charge_account();
        }
        return this;
    }

    public void back_to_contract() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        clickOn(locate(Button, "Back to Contract"));
        Selenide.switchTo().defaultContent();
    }

    private EditLodgementPointPopUp editLodgementPointPopUp;

    public ManageAccountNumber(WebDriver driver) {
        super(driver);
        setEditLodgementPointPopUp(new EditLodgementPointPopUp(lDriver));
    }
}
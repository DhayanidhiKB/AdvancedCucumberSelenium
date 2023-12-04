package com.my.salesforce.sandbox.application.pages.contract.details;

import com.codeborne.selenide.Selenide;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.ContractPageElements;
import com.my.salesforce.sandbox.application.pages.contract.dov.DOVCategory;
import com.my.salesforce.sandbox.application.pages.contract.renewal.RenewContract;
import lombok.*;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class Steps extends BasePage implements ContractPageElements {
    public Steps initiate_dov() {
        isItInteractable(locate(CurrentStage, "In Effect")).scrollIntoView(true);
        clickOn(locate(Action, "DoV"));
        return this;
    }

    public Steps initiate_renewal() {
        Selenide.refresh();
        isItInteractable(locate(CurrentStage, "In Effect")).scrollIntoView(true);
        executeJavaScript("window.scrollBy(0,150)");
        clickOn(locate(Action, "Renew"));
        return this;
    }

    private DOVCategory dovCategory;
    private RenewContract renewContract;

    public Steps(WebDriver driver) {
        super(driver);
        setDovCategory(new DOVCategory(lDriver));
        setRenewContract(new RenewContract(lDriver));
    }
}
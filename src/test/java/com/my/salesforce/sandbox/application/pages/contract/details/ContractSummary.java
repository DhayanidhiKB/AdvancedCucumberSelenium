package com.my.salesforce.sandbox.application.pages.contract.details;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.ContractPageElements;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class ContractSummary extends BasePage implements ContractPageElements {
    public void set(String pricing_category) {
        clickOn(locate(Edit, "Pricing Category"));
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        executeJavaScript("window.scrollBy(0,100)");
        clickOn(locate(ListBox, "Pricing Category"));
        clickOn(locate(ListMenus, pricing_category));
        clickOn(locate(Button, "Save"));
        isItInteractable(locate(Edit, "Pricing Category"));
        browser.refresh();
    }

    public void sign(String contractSteps, String contractStepStatus, String approvalStatus) {
        locate(Edit, "Amendment Effective Date").scrollIntoView(true);
        isItInteractable(locate(Edit, "Contract Steps"));
        actions().moveToElement(locate(Edit, "Contract Steps"))
                .pause(Duration.ofSeconds(1)).click(locate(Edit, "Contract Steps"))
                .build().perform();
        clickOn(locate(ListBox, "Contract Steps"));
        clickOn(locate(ListMenus, contractSteps));
        clickOn(locate(ListBox, "Contract Step Status"));
        clickOn(locate(ListMenus, contractStepStatus));
        clickOn(locate(ListBox, "Approval Status"));
        clickOn(locate(ListMenus, approvalStatus));
        locate(ListBox, "Contract Step Status").scrollIntoView(true);
        actions().moveToElement($(IsDocumentSigned)).click($(IsDocumentSigned)).build().perform();
        clickOn(locate(Button, "Save"));
    }

    public void activate(String contractSteps, String contractStepStatus) {
        locate(Edit, "Amendment Effective Date").scrollIntoView(true);
        isItInteractable(locate(Edit, "Contract Steps"));
        actions().moveToElement(locate(Edit, "Contract Steps"))
                .pause(Duration.ofSeconds(1)).click(locate(Edit, "Contract Steps"))
                .build().perform();
        clickOn(locate(ListBox, "Contract Steps"));
        clickOn(locate(ListMenus, contractSteps));
        clickOn(locate(ListBox, "Contract Step Status"));
        clickOn(locate(ListMenus, contractStepStatus));
        clickOn(locate(Button, "Save"));
    }

    public void verify(String step, String status) {
        isItInteractable(locate(Edit, "Contract Steps"));
        isItInteractable(locate(ContractSteps, step));
        isItInteractable(locate(ContractStepStatus, status));
        browser.refresh();
        isItInteractable(locate(StageAhead, "Expired"));
        isItInteractable(locate(CurrentStage, step));
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
    }

    public ContractSummary(WebDriver driver) {
        super(driver);
    }
}
package com.my.salesforce.sandbox.application.pages.shopping_cart.eparcel;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.eparcel.SubmitForApprovalElements;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.*;

public class SubmitForApproval extends BasePage implements SubmitForApprovalElements {
    public SubmitForApproval is_ready() {
        isItVisible(Header);
        isItVisible(PageHeader);
        isItVisible(PageBody);
        isItInteractable(ReturnToCart);
        isItInteractable(SubmitWithAttachments);
        isItInteractable(Submit);
        isItInteractable(CartLink);
        return this;
    }

    public SubmitForApproval submit(String comments) {
        Submit.shouldBe(interactable).click();
        setValue(comments, SubmissionComments);
        Submit.shouldBe(interactable).click();
        return this;
    }

    public void return_to_cart() {
        isItVisible(MessageCell);
        clickOn(Return);
    }

    public SubmitForApproval(WebDriver driver) {
        super(driver);
    }
}
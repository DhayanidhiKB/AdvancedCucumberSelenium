package com.my.salesforce.sandbox.application.pages.shopping_cart.eparcel;

import com.codeborne.selenide.Selenide;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.eparcel.ManageLodgementPointsPageElements;
import org.openqa.selenium.WebDriver;

public class ManageLodgementPointsPage extends BasePage implements ManageLodgementPointsPageElements {
    public ManageLodgementPointsPage is_ready() {
        Selenide.switchTo().defaultContent();
        isItVisible(MainTitle);
        isItInteractable(locate(Input, "Search LP"));
        isItInteractable(locate(Input, "Add LP"));
        isItInteractable(locate(Input, "Back to Shopping Cart"));
        return this;
    }

    public void add_lodgement_point(String postcode) {
        setValue(postcode, PostCode);
        clickOn(locate(Input, "Search LP"));
        isItInteractable(PostCode);
        clickOn(locate(Lp, "MELBOURNE BOURKE STREET"));
        clickOn(locate(Input, "Add LP"));
        isItInteractable(SelectedLP);
        clickOn(locate(Input, "Back to Shopping Cart"));
    }

    public ManageLodgementPointsPage(WebDriver driver) {
        super(driver);
    }
}
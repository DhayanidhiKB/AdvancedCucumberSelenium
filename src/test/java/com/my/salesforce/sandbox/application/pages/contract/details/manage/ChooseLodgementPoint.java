package com.my.salesforce.sandbox.application.pages.contract.details.manage;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.details.ManageLodgementPointsAndAccountNumbersPageElements;
import org.openqa.selenium.WebDriver;

public class ChooseLodgementPoint extends BasePage implements ManageLodgementPointsAndAccountNumbersPageElements {
    public void search_and_choose(String post_code) {
        setValue("3810", locate(Input, "Post Code"));
        clickOn(locate(Button, "Search LP"));
        isItInteractable(locate(Input, "Post Code"));
        clickOn(locate(Lp, post_code));
        clickOn(locate(Button, "Add LP"));
    }

    public ChooseLodgementPoint(WebDriver driver) {
        super(driver);
    }
}
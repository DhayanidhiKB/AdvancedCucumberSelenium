package com.my.salesforce.sandbox.application.pages.dsr.customer_onboarding;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.CustomerOnboardingDSRPageElements;
import org.openqa.selenium.WebDriver;

public class WorkTypeDropList extends BasePage implements CustomerOnboardingDSRPageElements {
    public void select(String work_type, String support_work_type) {
        clickOn(locate(ListBox, "Work Type"));
        locate(ListMenu, work_type).scrollIntoView(true);
        clickOn(locate(ListMenu, work_type));
        isItInteractable(locate(SupportWorkType, support_work_type));
    }

    public WorkTypeDropList(WebDriver driver) {
        super(driver);
    }
}
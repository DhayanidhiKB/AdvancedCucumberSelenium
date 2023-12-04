package com.my.salesforce.sandbox.application.pages.contract.details.lodgement_point;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.details.LodgementPointPageElements;
import org.openqa.selenium.WebDriver;

public class LodgementPoint extends BasePage implements LodgementPointPageElements {
    public LodgementPoint is_ready() {
        isItVisible(Header);
        isItInteractable(locate(Edit, "Status"));
        return this;
    }

    public LodgementPoint verify(String status, String lodgement_point, String integration_status, String integration_status_description) {
        isItVisible(locate(Text, "Status", status));
        isItVisible(locate(Text, "Lodgement Point", lodgement_point));
        isItVisible(locate(Text, "Integration Status", integration_status));
        isItVisible(locate(Text, "Integration Status Description",
                integration_status_description));
        return this;
    }

    public void back_to_contract(String contractName) {
        clickOn(locate(NavigationBar, contractName));
    }

    public LodgementPoint(WebDriver driver) {
        super(driver);
    }
}
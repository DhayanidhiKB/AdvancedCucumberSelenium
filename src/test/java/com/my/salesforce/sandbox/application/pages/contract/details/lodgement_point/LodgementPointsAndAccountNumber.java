package com.my.salesforce.sandbox.application.pages.contract.details.lodgement_point;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.details.LodgementPointsAndAccountNumberPageElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class LodgementPointsAndAccountNumber extends BasePage implements LodgementPointsAndAccountNumberPageElements {
    public LodgementPointsAndAccountNumber is_ready() {
        isItVisible(Header);
        isItInteractable(Manage);
        return this;
    }

    private LodgementPoint lodgementPointPage;

    public LodgementPointsAndAccountNumber(WebDriver driver) {
        super(driver);
        setLodgementPointPage(new LodgementPoint(lDriver));
    }
}
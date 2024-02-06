package com.my.salesforce.sandbox.application.pages.contract.details.manage;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.details.ManageLodgementPointsAndAccountNumbersPageElements;
import com.my.salesforce.sandbox.application.pages.contract.ContractHeader;
import lombok.*;
import org.openqa.selenium.*;

@Getter
@Setter
public class ManageLodgementPointsAndAccountNumbers extends BasePage implements ManageLodgementPointsAndAccountNumbersPageElements {
    public ManageLodgementPointsAndAccountNumbers is_ready() {
        switch_to_frame(iFrame);
        isItVisible(MainTitle);
        isItInteractable(locate(Button, "Add Product Specific Lodgement Point"));
        isItInteractable(locate(Button, "Add Generic Lodgement Point"));
        isItInteractable(locate(Button, "Back to Contract"));
        return this;
    }

    private ManageAccountNumber manageAccountNumber;
    private ChooseLodgementPoint chooseLodgementPoint;
    private ContractHeader contractHeader;

    public ManageLodgementPointsAndAccountNumbers(WebDriver driver) {
        super(driver);
        setManageAccountNumber(new ManageAccountNumber(lDriver));
        setChooseLodgementPoint(new ChooseLodgementPoint(lDriver));
        setContractHeader(new ContractHeader(lDriver));
    }
}
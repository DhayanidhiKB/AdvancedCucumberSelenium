package com.my.salesforce.sandbox.application.pages.contract.details.manage;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.details.ManageContractRelationshipsPageElements;
import com.my.salesforce.sandbox.application.pages.contract.ContractHeader;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class ManageContractRelationships extends BasePage implements ManageContractRelationshipsPageElements {
    public ManageContractRelationships is_ready() {
        isItVisible(Header);
        isItVisible(SubHeader);
        isItInteractable(locate(Button, "Add Contract Relationship"));
        isItInteractable(locate(Button, "Add Product specific Billing Account"));
        return this;
    }

    public ManageContractRelationships add(@NotNull String contract_relationship, String relationship_level) {
        if (contract_relationship.equals("Add Contract Relationship")) {
            clickOn(locate(Button, contract_relationship));
            clickOn(locate(RadioOption, relationship_level));
        } else {
            clickOn(locate(Button, contract_relationship));
            clickOn(AppcProduct);
            clickOn(locate(RadioOption, relationship_level));
            isItInteractable(locate(RadioOption, relationship_level)).scrollIntoView(true);
        }
        return this;
    }

    public void select_required(@NotNull String contract_relationship, String lodgement_point) {
        locate(Button, contract_relationship).scrollIntoView(true);
        setValue(lodgement_point, SearchLodgementPoint);
        clickOn(locate(SearchOption, lodgement_point));
        isItInteractable(locate(LodgementPointName, lodgement_point));
    }

    public ManageContractRelationships select_charge_account() {
        isItInteractable($$(SelectAll).get(0)).scrollIntoView(true).click();
        return this;
    }

    public void select_sub_account() {
        isItInteractable($$(SelectAll).get(1)).scrollIntoView(true).click();
    }

    public ManageContractRelationships apply_relationship() {
        isItInteractable(locate(Button, "Apply Relationship")).scrollIntoView(true).click();
        return this;
    }

    public ManageContractRelationships verify_added_billing_account(@NotNull String relationship_level) {
        isItVisible(TableTitle).scrollIntoView(true);
        if (relationship_level.contains("Organisation")) {
            $$(AddedBillingAccount).shouldHave(size(1));
        } else {
            $$(AddedBillingAccount).shouldHave(size(2));
        }
        return this;
    }

    public void back_to_contract() {
        locate(Button, "Back to Apttus Contract").scrollIntoView(true);
        clickOn(locate(Button, "Back to Apttus Contract"));
    }

    private ContractHeader contractHeader;

    public ManageContractRelationships(WebDriver driver) {
        super(driver);
        setContractHeader(new ContractHeader(lDriver));
    }
}
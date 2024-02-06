package com.my.salesforce.sandbox.application.pages.contract;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.ConfirmContractDetailsElements;
import com.my.salesforce.sandbox.application.pages.contract.details.manage.ManageContractRelationships;
import com.my.salesforce.sandbox.application.pages.contract.details.manage.ManageLodgementPointsAndAccountNumbers;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

@Getter
@Setter
public class ConfirmContractDetails extends BasePage implements ConfirmContractDetailsElements {
    public ConfirmContractDetails is_ready() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(40));
        Spinner.shouldBe(disappear).shouldBe(hidden).shouldHave(disappear);
        isItVisible(Header);
        isItInteractable(CloseThePage);
        isItInteractable(Next);
        CloseThePage.shouldBe(visible).shouldBe(enabled).shouldBe(interactable).shouldHave(appear);
        return this;
    }

    public ConfirmContractDetails key_contact_is(String contact) {
        isItVisible(locate(KeyContact, contact));
        return this;
    }

    public ConfirmContractDetails set_contract_condition_as(String contract_condition) {
        clickOn(ContractCondition);
        clickOn(locate(ContractConditionOption, contract_condition));
        return this;
    }

    public ConfirmContractDetails enter_term(String months) {
        setValue(months, TermOfAgreement);
        return this;
    }

    public ConfirmContractDetails contract_entity_is(String contract_entity) {
        isItInteractable(locate(ContractingEntity, contract_entity));
        return this;
    }

    public ConfirmContractDetails contract_type_is(String contract_type) {
        isItVisible(locate(ContractType, contract_type));
        return this;
    }

    public ConfirmContractDetails included_product(String product) {
        isItInteractable(locate(IncludedProduct, product));
        return this;
    }

    public ConfirmContractDetails next() {
        isItInteractable(Next).scrollIntoView(true);
        isItInteractable(Next).scrollIntoView(true).click();
        return this;
    }

    private ManageContractRelationships manageContractRelationshipsPage;
    private ManageLodgementPointsAndAccountNumbers manageLodgementPointsAndAccountNumbersPage;

    public ConfirmContractDetails(WebDriver driver) {
        super(driver);
        setManageContractRelationshipsPage(new ManageContractRelationships(lDriver));
        setManageLodgementPointsAndAccountNumbersPage(new ManageLodgementPointsAndAccountNumbers(lDriver));
    }
}
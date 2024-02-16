package com.my.salesforce.sandbox.application.pages.contract;

import com.codeborne.selenide.Selenide;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.ContractPageElements;
import com.my.salesforce.sandbox.application.pages.contract.details.*;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

@Getter
@Setter
public class Contract extends BasePage implements ContractPageElements {
    public Contract create_contract() {
        clickOn(CreateContract);
        return this;
    }

    public Contract unmerge_document(){
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        switch_to_frame(iFrame);
        clickOn(UnmergeDocument);
        Selenide.switchTo().defaultContent();
        return this;
    }

    public Contract refresh_and_wait() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        Selenide.refresh();
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        Selenide.refresh();
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        return this;
    }

    public Contract set(String pricing_category) {
        isItInteractable(CreateContract).scrollIntoView(true);
        getContractSummary().set(pricing_category);
        return this;
    }

    public void create_contract_documents() {
        switch_to_frame(iFrame);
        isItInteractable(GenerateDocsForm);
        isItInteractable(CreateContractDocuments).scrollIntoView(true).click();
        Selenide.switchTo().defaultContent();
    }

    public void merge_documents() {
        switch_to_frame(iFrame);
        clickOn(MergeDocuments);
        Selenide.switchTo().defaultContent();
    }

    public Contract signing_the_contract(String contractSteps, String contractStepStatus, String approvalStatus) {
        getContractSummary().sign(contractSteps, contractStepStatus, approvalStatus);
        return this;
    }

    public Contract activate_the_contract(String contractSteps, String contractStepStatus) {
        getContractSummary().activate(contractSteps, contractStepStatus);
        return this;
    }

    public Contract verify(String step, String status) {
        getContractSummary().verify(step, status);
        return this;
    }

    private ContractRelatedLinks contractRelatedLinks;
    private Steps steps;
    private ContractSummary contractSummary;

    public Contract(WebDriver driver) {
        super(driver);
        setContractRelatedLinks(new ContractRelatedLinks(lDriver));
        setSteps(new Steps(lDriver));
        setContractSummary(new ContractSummary(lDriver));
    }
}
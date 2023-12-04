package com.my.salesforce.sandbox.application.pages.contract;

import com.codeborne.selenide.Selenide;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.ContractPageElements;
import com.my.salesforce.sandbox.application.pages.contract.details.ManualContractActions;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

@Getter
@Setter
public class ContractHeader extends BasePage implements ContractPageElements {
    public ContractHeader is_ready() {
        isItVisible(Header);
        isItInteractable(locate(StageAhead, "In Signatures"));
        isItInteractable(locate(StageAhead, "In Effect"));
        return this;
    }

    public ContractHeader contract_is_active() {
        locate(CurrentStage, "In Effect").shouldBe(interactable).shouldHave(appear);
        return this;
    }

    public ContractHeader page_refresh() {
        Selenide.refresh();
        is_ready();
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        return this;
    }

    public String get_name() {
        return Name.shouldBe(visible).shouldHave(appear).getText();
    }

    public ContractHeader verify_contract_details(String opportunity, String proposal) {
        isItInteractable(locate(Related, "Opportunity", opportunity));
        isItInteractable(locate(Related, "Proposal", proposal));
        return this;
    }

    public ContractHeader upload_and_merge(String file) {
        RequestChanges.scrollIntoView(true);
        getManualContractActions().is_ready().import_offline_document(file);
        is_ready();
        RequestChanges.scrollIntoView(true);
        getManualContractActions().is_ready().merge_documents();
        return this;
    }

    public ContractHeader ready_to_sign() {
        page_refresh_and_wait();
        locate(StageAhead, "In Signatures").shouldBe(interactable).shouldHave(appear);
        page_refresh_and_wait();
        locate(StageAhead, "In Signatures").shouldBe(interactable).shouldHave(appear);
        page_refresh_and_wait();
        locate(StageAhead, "In Signatures").shouldBe(interactable).shouldHave(appear);
        locate(StageAhead, "In Effect").shouldBe(interactable).shouldHave(appear);
        locate(CurrentStage, "In Authoring").shouldBe(interactable).shouldHave(appear);
        return this;
    }

    private ManualContractActions manualContractActions;
    private Contract contractPage;

    public ContractHeader(WebDriver driver) {
        super(driver);
        setManualContractActions(new ManualContractActions(lDriver));
        setContractPage(new Contract(lDriver));
    }
}
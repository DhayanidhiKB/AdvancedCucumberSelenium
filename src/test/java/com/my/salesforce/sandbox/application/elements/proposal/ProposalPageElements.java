package com.my.salesforce.sandbox.application.elements.proposal;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface ProposalPageElements {
    SelenideElement Header = $(By.xpath("//div[text()='Proposal']"));
    SelenideElement Name = $(By.xpath("//div[text()='Proposal']/following-sibling::slot/lightning-formatted-text"));
    String Action = "//runtime_platform_actions-action-renderer[@title='%value%']/parent::li[contains(@data-target-selection-name,'Proposal')]";
    SelenideElement MoreActions = $(By.xpath("//span[text()='Show more actions']/ancestor::lightning-button-menu[contains(@data-target-reveals,'Proposal')]"));
    String ProposalStage = "//a[@title='%value%' and @aria-selected='true']";
    String Search = "//span[text()='%value%']/parent::label/following-sibling::div/descendant::input";
    String ProposalStageAhead = "//a[@title='%value%' and @aria-selected='false']";
    String ProposalStep = "//img[@alt='%value%']/parent::a";
    SelenideElement iFrame = $(By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]"));
    String AttachementCheckBox = "//td[contains(text(),'%value%')]/preceding-sibling::td/input[@type='checkbox']";
    SelenideElement CancelProposal = $(By.xpath("//input[@value='Cancel']"));
    SelenideElement ProceedWithProposal = $(By.xpath("//input[@value='Next']"));
    SelenideElement AttachementContentType = $(By.xpath("//div[text()='Content Type']//ancestor::thead/following-sibling::tbody/tr/td[text()='PDF']"));
    SelenideElement CreateContract = $(By.xpath("//img[@alt='Create']/parent::a[contains(@href,'/c__APT_ContractServiceDetailsWrapper?')]"));
    SelenideElement Amendment = $(By.xpath("//a[contains(@href,'isAmend=true')]"));
    String Section = "//span[@class='test-id__section-header-title' and text()='%value%']";
}
package com.my.salesforce.sandbox.application.elements.contract;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface ConfirmContractDetailsElements {
    SelenideElement Spinner = $(By.xpath("//lightning-spinner[@class='spins slds-spinner_container']"));
    SelenideElement CloseThePage = $(By.xpath("//button[@title='Closing the page without saving any changes']"));
    SelenideElement Header = $(By.xpath("//div[text()='Confirm Contract Details']"));
    String ContractingEntity = "//label[text()='Contracting Entity']/following-sibling::div/descendant::button[@data-value='%value%']";
    String KeyContact = "//label[text()='Key Contact']/following-sibling::div/descendant::input[@data-value='%value%']";
    SelenideElement ContractCondition = $(By.xpath("//label[text()='Contract Condition']/following-sibling::div/descendant::button"));
    String ContractConditionOption = "//lightning-base-combobox-item[@data-value='%value%']";
    String ContractType = "//label[text()='Contract Type']/following-sibling::div/descendant::button[@data-value='%value%']";
    String IncludedProduct = "//div[text()='Included Product Lines']/following-sibling::div/descendant::span[text()='Chosen']/following-sibling::div/descendant::div[@data-value='%value%']";
    SelenideElement TermOfAgreement = $(By.xpath("(//label[text()='Term of Agreement (Months)']/following-sibling::div/input)[2]"));
    SelenideElement Next = $(By.xpath("//button[text()='Next']"));
}
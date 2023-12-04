package com.my.salesforce.sandbox.application.elements.welcome;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public interface HeaderElements {
    SelenideElement Search = $(By.xpath("//button[normalize-space()='Search...']"));
    SelenideElement SearchInput = $(By.xpath("//div[@data-aura-class='forceSearchInputEntitySelector']/following-sibling::lightning-input/descendant::input"));
    String SearchResult = "//span[@title='%value%']";
    SelenideElement GlobalActions = $(".slds-global-actions");
    SelenideElement ProfileCard = $(".slds-button.branding-userProfile-button.slds-button.slds-global-actions__avatar.slds-global-actions__item-action.forceHeaderButton");
    SelenideElement LogOut = $(".profile-link-label.logout.uiOutputURL");
}
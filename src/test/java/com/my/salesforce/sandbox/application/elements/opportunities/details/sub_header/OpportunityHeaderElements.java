package com.my.salesforce.sandbox.application.elements.opportunities.details.sub_header;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface OpportunityHeaderElements {
    String OpportunityName = "//div[text()='Opportunity']/parent::h1/descendant::lightning-formatted-text[text()='%value%']";
    SelenideElement Follow = $(By.xpath("//div[@data-target-selection-name='sfdc:StandardButton.Opportunity.Follow']"));
    SelenideElement Edit = $(By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.Opportunity.Edit']"));
    SelenideElement EditOnboarding = $(By.xpath("//li[@data-target-selection-name='sfdc:QuickAction.Opportunity.Edit_Onboarding']"));
    SelenideElement AddPricingProducts = $(By.xpath("//li[@data-target-selection-name='sfdc:QuickAction.Opportunity.Add_Product']"));
    SelenideElement BulkEditProducts = $(By.xpath("//li[@data-target-selection-name='sfdc:CustomButton.Opportunity.Bulk_Edit_Products']"));
    By NewDealSupportRequestLink = By.xpath("//li[@data-target-selection-name='sfdc:QuickAction.Opportunity.New_Deal_Support_Request']");
    SelenideElement NewDealSupportRequestSubLink = $(By.xpath("//lightning-menu-item[@data-target-selection-name='sfdc:QuickAction.Opportunity.New_Deal_Support_Request']"));
    SelenideElement MoreLinks = $(By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.Opportunity.Edit']/following-sibling::li[contains(@class,'slds-dropdown-trigger')]"));
    By CloseOpportunityLink = By.xpath("//li[@data-target-selection-name='sfdc:QuickAction.Opportunity.Opportunity_Closure']");
    SelenideElement CloseOpportunitySubLink = $(By.xpath("//lightning-menu-item[@data-target-selection-name='sfdc:QuickAction.Opportunity.Opportunity_Closure']"));
    String OpportunityStage = "//li[@data-name='%value%']";
}
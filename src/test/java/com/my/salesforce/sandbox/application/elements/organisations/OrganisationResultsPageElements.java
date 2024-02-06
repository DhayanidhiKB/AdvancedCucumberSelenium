package com.my.salesforce.sandbox.application.elements.organisations;

import org.openqa.selenium.By;

public interface OrganisationResultsPageElements {
    By Header = By.xpath("//h2[text()='Organisation Results']");
    By Search = By.xpath("//h2[text()='Organisation Results']/parent::div/following-sibling::div/descendant::input[@title='Search Organisations']");
    String Link = "//div[text()='Organisations']/parent::h2/ancestor::div[@data-aura-class='forceSearchResultsRegion']/descendant::a[@title='%value%']";
}
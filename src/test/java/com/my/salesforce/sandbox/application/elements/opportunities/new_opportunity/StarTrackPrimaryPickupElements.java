package com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface StarTrackPrimaryPickupElements {
    SelenideElement Header = $(By.xpath("//h2[text()='StarTrack Primary Pickup (Postcode)']"));
    SelenideElement Search = $(By.xpath("//h2[text()='StarTrack Primary Pickup (Postcode)']/parent::div/following-sibling::div/descendant::input[@title='Search Post Code Mapping...']"));
    String Link = "//a[text()='%value%']";
}
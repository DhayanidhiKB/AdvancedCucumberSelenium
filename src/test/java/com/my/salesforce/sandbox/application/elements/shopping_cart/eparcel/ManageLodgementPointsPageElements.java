package com.my.salesforce.sandbox.application.elements.shopping_cart.eparcel;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public interface ManageLodgementPointsPageElements {
    String Input = "//input[@value='%value%']";
    SelenideElement MainTitle = $(By.xpath("//h2[text()='Add Product Lodgement Point']"));
    SelenideElement PostCode = $(By.xpath("//b[contains(text(),'Post Code')]/following-sibling::div//input"));
    String Lp = "//td[text()='%value%']/preceding-sibling::td/input";
    SelenideElement SelectedLP = $(By.xpath("//span[contains(@id,'selectedLP')]"));
}
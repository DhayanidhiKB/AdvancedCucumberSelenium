package com.my.salesforce.sandbox.application.elements.opportunities;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface AddProductsPageElements {
    SelenideElement Header = $(By.xpath("//h2[text()='Add Products']"));
    SelenideElement SearchProducts = $(By.xpath("//input[@title='Search Products']"));
    SelenideElement CloseWindow = $(By.xpath("//button[@title='Close this window']"));
}
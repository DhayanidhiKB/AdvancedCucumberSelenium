package com.my.salesforce.sandbox.application.elements.opportunities.details;

import org.openqa.selenium.By;

public interface EditProductsPageElements {
    By header = By.xpath("//h2[text()='Edit All Products']");
    String revenueStartDate = "(//span[text()='Revenue Start Date'])[2]/ancestor::thead/following-sibling::tbody//th//a[@title='']";
}
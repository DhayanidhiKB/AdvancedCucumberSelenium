package com.my.salesforce.sandbox.helpers.actions;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Get {
    private final WebDriver lDriver;

    public Get(WebDriver driver) {
        this.lDriver = driver;
    }

    public String pageTitle() {
        return this.lDriver.getTitle().trim();
    }

    public String currentUrl() {
        return this.lDriver.getCurrentUrl().trim();
    }

    public String pageSource() {
        return this.lDriver.getPageSource().trim();
    }

    public String text(@NotNull WebElement element) {
        return element.getText().trim();
    }

    public String tagName(@NotNull WebElement element) {
        return element.getTagName();
    }

    public String attribute(@NotNull WebElement element, String name) {
        return element.getAttribute(name);
    }

    public String domAttribute(@NotNull WebElement element, String name) {
        return element.getDomAttribute(name);
    }

    public String domProperty(@NotNull WebElement element, String name) {
        return element.getDomProperty(name);
    }

    public String cssValue(@NotNull WebElement element, String propertyName) {
        return element.getCssValue(propertyName);
    }
}
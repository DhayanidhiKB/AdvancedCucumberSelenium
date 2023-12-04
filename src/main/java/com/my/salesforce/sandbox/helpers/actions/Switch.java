package com.my.salesforce.sandbox.helpers.actions;

import com.my.salesforce.sandbox.enums.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

public class Switch {
    private final WebDriver lDriver;

    public Switch(WebDriver driver) {
        this.lDriver = driver;
    }

    public WebDriver to(@NotNull New condition) {
        switch (condition) {
            case tab:
                return this.lDriver.switchTo().newWindow(WindowType.TAB);
            case window:
                return this.lDriver.switchTo().newWindow(WindowType.WINDOW);
            default:
                throw new RuntimeException("Double check parameter: enum condition");
        }
    }

    public WebDriver to(@NotNull Frame condition, String value) {
        switch (condition) {
            case byIdOrName:
                return this.lDriver.switchTo().frame(value);
            case byIndex:
                return this.lDriver.switchTo().frame(Integer.parseInt(value));
            default:
                throw new RuntimeException("Double check parameters: enum condition (or) string");
        }
    }

    public WebDriver to(@NotNull Frame condition, WebElement element) {
        if (condition.equals(Frame.byElement)) {
            return this.lDriver.switchTo().frame(element);
        } else {
            throw new RuntimeException("Double check parameters: enum condition (or) element");
        }
    }

    public WebDriver to(@NotNull Frame condition) {
        if (condition.equals(Frame.parent)) {
            return this.lDriver.switchTo().parentFrame();
        } else {
            throw new RuntimeException("Double check parameter: enum condition");
        }
    }

    public WebDriver defaultContent() {
        return this.lDriver.switchTo().defaultContent();
    }
}
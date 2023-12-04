package com.my.salesforce.sandbox.helpers.actions;

import com.my.salesforce.sandbox.enums.Is;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Check {
    private final WebDriver lDriver;

    public Check(WebDriver driver) {
        this.lDriver = driver;
    }

    private static final Map<Is, Function<WebElement, Boolean>> map1 = new HashMap<>();
    private static final Function<WebElement, Boolean> IsDisplayed = WebElement::isDisplayed;
    private static final Function<WebElement, Boolean> IsEnabled = WebElement::isEnabled;
    private static final Function<WebElement, Boolean> IsSelected = WebElement::isSelected;

    static {
        map1.put(Is.displayed, IsDisplayed);
        map1.put(Is.enabled, IsEnabled);
        map1.put(Is.selected, IsSelected);
    }

    public boolean whether(WebElement element, @NotNull Is condition) {
        return map1.get(condition).apply(element);
    }

    private static final Map<Is, BiFunction<By, WebDriver, Boolean>> map2 = new HashMap<>();
    private static final BiFunction<By, WebDriver, Boolean> Present = (locator, d) -> d.findElements(locator).size() > 0;
    private static final BiFunction<By, WebDriver, Boolean> Displayed = (locator, d) -> d.findElement(locator).isDisplayed();
    private static final BiFunction<By, WebDriver, Boolean> Enabled = (locator, d) -> d.findElement(locator).isEnabled();
    private static final BiFunction<By, WebDriver, Boolean> Selected = (locator, d) -> d.findElement(locator).isSelected();

    static {
        map2.put(Is.present, Present);
        map2.put(Is.displayed, Displayed);
        map2.put(Is.enabled, Enabled);
        map2.put(Is.selected, Selected);
    }

    public boolean whether(By locator, @NotNull Is condition) {
        return map2.get(condition).apply(locator, lDriver);
    }
}
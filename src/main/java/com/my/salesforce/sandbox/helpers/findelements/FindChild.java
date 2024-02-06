package com.my.salesforce.sandbox.helpers.findelements;

import com.my.salesforce.sandbox.enums.ByLocator;
import com.my.salesforce.sandbox.enums.Relatively;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class FindChild {
    private static final Map<ByLocator, BiFunction<WebElement, String, WebElement>> map1 = new HashMap<>();
    private static final BiFunction<WebElement, String, WebElement> Id = (p, locatorValue) -> p.findElement(By.id(locatorValue));
    private static final BiFunction<WebElement, String, WebElement> Name = (p, locatorValue) -> p.findElement(By.name(locatorValue));
    private static final BiFunction<WebElement, String, WebElement> ClassName = (p, locatorValue) -> p.findElement(By.className(locatorValue));
    private static final BiFunction<WebElement, String, WebElement> LinkText = (p, locatorValue) -> p.findElement(By.linkText(locatorValue));
    private static final BiFunction<WebElement, String, WebElement> PartialLinkText = (p, locatorValue) -> p.findElement(By.partialLinkText(locatorValue));
    private static final BiFunction<WebElement, String, WebElement> TagName = (p, locatorValue) -> p.findElement(By.tagName(locatorValue));
    private static final BiFunction<WebElement, String, WebElement> Xpath = (p, locatorValue) -> p.findElement(By.xpath(locatorValue));
    private static final BiFunction<WebElement, String, WebElement> Css = (p, locatorValue) -> p.findElement(By.cssSelector(locatorValue));

    static {
        map1.put(ByLocator.id, Id);
        map1.put(ByLocator.name, Name);
        map1.put(ByLocator.className, ClassName);
        map1.put(ByLocator.linkText, LinkText);
        map1.put(ByLocator.partialLinkText, PartialLinkText);
        map1.put(ByLocator.tagName, TagName);
        map1.put(ByLocator.xpath, Xpath);
        map1.put(ByLocator.css, Css);
    }

    public WebElement element(@NotNull ByLocator byLocator, WebElement parent, String locatorValue) {
        return map1.get(byLocator).apply(parent, locatorValue);
    }

    private static final Map<Relatively, TriFunction<WebElement, By, By, WebElement>> map2 = new HashMap<>();
    private static final TriFunction<WebElement, By, By, WebElement> Above = (p, locator, e) -> p.findElement(with(locator).above(e));
    private static final TriFunction<WebElement, By, By, WebElement> Below = (p, locator, e) -> p.findElement(with(locator).below(e));
    private static final TriFunction<WebElement, By, By, WebElement> Near = (p, locator, e) -> p.findElement(with(locator).near(e));
    private static final TriFunction<WebElement, By, By, WebElement> RightOf = (p, locator, e) -> p.findElement(with(locator).toRightOf(e));
    private static final TriFunction<WebElement, By, By, WebElement> LeftOf = (p, locator, e) -> p.findElement(with(locator).toLeftOf(e));

    static {
        map2.put(Relatively.above, Above);
        map2.put(Relatively.below, Below);
        map2.put(Relatively.near, Near);
        map2.put(Relatively.rightOf, RightOf);
        map2.put(Relatively.leftOf, LeftOf);
    }

    public WebElement element(@NotNull Relatively relatively, WebElement parent, By locator, By element) {
        return map2.get(relatively).apply(parent, locator, element);
    }
}
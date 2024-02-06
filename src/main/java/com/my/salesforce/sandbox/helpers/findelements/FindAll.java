package com.my.salesforce.sandbox.helpers.findelements;

import com.my.salesforce.sandbox.enums.ByLocator;
import com.my.salesforce.sandbox.enums.Relatively;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class FindAll {
    private final WebDriver lDriver;

    public FindAll(WebDriver driver) {
        this.lDriver = driver;
    }

    public List<WebElement> element(By locator) {
        return this.lDriver.findElements(locator);
    }

    public List<WebElement> element(By locator, @NotNull WebElement element) {
        return element.findElements(locator);
    }

    private static final Map<ByLocator, BiFunction<String, WebDriver, List<WebElement>>> map1 = new HashMap<>();
    private static final BiFunction<String, WebDriver, List<WebElement>> Id = (locator, d) -> d.findElements(By.id(locator));
    private static final BiFunction<String, WebDriver, List<WebElement>> Name = (locator, d) -> d.findElements(By.name(locator));
    private static final BiFunction<String, WebDriver, List<WebElement>> ClassName = (locator, d) -> d.findElements(By.className(locator));
    private static final BiFunction<String, WebDriver, List<WebElement>> LinkText = (locator, d) -> d.findElements(By.linkText(locator));
    private static final BiFunction<String, WebDriver, List<WebElement>> PartialLinkText = (locator, d) -> d.findElements(By.partialLinkText(locator));
    private static final BiFunction<String, WebDriver, List<WebElement>> TagName = (locator, d) -> d.findElements(By.tagName(locator));
    private static final BiFunction<String, WebDriver, List<WebElement>> Xpath = (locator, d) -> d.findElements(By.xpath(locator));
    private static final BiFunction<String, WebDriver, List<WebElement>> Css = (locator, d) -> d.findElements(By.cssSelector(locator));

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

    public List<WebElement> element(@NotNull ByLocator byLocator, String locatorValue) {
        return map1.get(byLocator).apply(locatorValue, lDriver);
    }

    private static final Map<Relatively, TriFunction<By, By, WebDriver, List<WebElement>>> map2 = new HashMap<>();
    private static final TriFunction<By, By, WebDriver, List<WebElement>> Above = (e, locator, d) -> d.findElements(with(locator).above(e));
    private static final TriFunction<By, By, WebDriver, List<WebElement>> Below = (e, locator, d) -> d.findElements(with(locator).below(e));
    private static final TriFunction<By, By, WebDriver, List<WebElement>> Near = (e, locator, d) -> d.findElements(with(locator).near(e));
    private static final TriFunction<By, By, WebDriver, List<WebElement>> RightOf = (e, locator, d) -> d.findElements(with(locator).toRightOf(e));
    private static final TriFunction<By, By, WebDriver, List<WebElement>> LeftOf = (e, locator, d) -> d.findElements(with(locator).toLeftOf(e));

    static {
        map2.put(Relatively.above, Above);
        map2.put(Relatively.below, Below);
        map2.put(Relatively.near, Near);
        map2.put(Relatively.rightOf, RightOf);
        map2.put(Relatively.leftOf, LeftOf);
    }

    public List<WebElement> element(@NotNull Relatively relatively, By element, By locator) {
        return map2.get(relatively).apply(element, locator, lDriver);
    }
}
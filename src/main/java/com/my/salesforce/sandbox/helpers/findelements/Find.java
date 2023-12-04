package com.my.salesforce.sandbox.helpers.findelements;

import com.my.salesforce.sandbox.enums.ByLocator;
import com.my.salesforce.sandbox.enums.Relatively;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Find {
    private final WebDriver lDriver;

    public Find(WebDriver driver) {
        this.lDriver = driver;
    }

    public WebElement element(By locator) {
        return this.lDriver.findElement(locator);
    }

    public WebElement element(@NotNull String locator, String wildCard) {
        return this.lDriver.findElement(By.xpath(locator.replace("%value%", wildCard)));
    }

    public WebElement element(@NotNull String locator, String wildCard1, String wildCard2) {
        return this.lDriver.findElement(By.xpath(locator
                .replace("%value1%", wildCard1).replace("%value2%", wildCard2)));
    }

    public WebElement element(By locator, @NotNull WebElement element) {
        return element.findElement(locator);
    }

    private static final Map<ByLocator, BiFunction<String, WebDriver, WebElement>> map1 = new HashMap<>();
    private static final BiFunction<String, WebDriver, WebElement> Id = (locator, d) -> d.findElement(By.id(locator));
    private static final BiFunction<String, WebDriver, WebElement> Name = (locator, d) -> d.findElement(By.name(locator));
    private static final BiFunction<String, WebDriver, WebElement> ClassName = (locator, d) -> d.findElement(By.className(locator));
    private static final BiFunction<String, WebDriver, WebElement> LinkText = (locator, d) -> d.findElement(By.linkText(locator));
    private static final BiFunction<String, WebDriver, WebElement> PartialLinkText = (locator, d) -> d.findElement(By.partialLinkText(locator));
    private static final BiFunction<String, WebDriver, WebElement> TagName = (locator, d) -> d.findElement(By.tagName(locator));
    private static final BiFunction<String, WebDriver, WebElement> Xpath = (locator, d) -> d.findElement(By.xpath(locator));
    private static final BiFunction<String, WebDriver, WebElement> Css = (locator, d) -> d.findElement(By.cssSelector(locator));

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

    public WebElement element(@NotNull ByLocator byLocator, String locatorValue) {
        return map1.get(byLocator).apply(locatorValue, lDriver);
    }

    private static final Map<Relatively, TriFunction<By, By, WebDriver, WebElement>> map2 = new HashMap<>();
    private static final TriFunction<By, By, WebDriver, WebElement> Above = (e, locator, d) -> d.findElement(with(locator).above(e));
    private static final TriFunction<By, By, WebDriver, WebElement> Below = (e, locator, d) -> d.findElement(with(locator).below(e));
    private static final TriFunction<By, By, WebDriver, WebElement> Near = (e, locator, d) -> d.findElement(with(locator).near(e));
    private static final TriFunction<By, By, WebDriver, WebElement> RightOf = (e, locator, d) -> d.findElement(with(locator).toRightOf(e));
    private static final TriFunction<By, By, WebDriver, WebElement> LeftOf = (e, locator, d) -> d.findElement(with(locator).toLeftOf(e));

    static {
        map2.put(Relatively.above, Above);
        map2.put(Relatively.below, Below);
        map2.put(Relatively.near, Near);
        map2.put(Relatively.rightOf, RightOf);
        map2.put(Relatively.leftOf, LeftOf);
    }

    public WebElement element(@NotNull Relatively relatively, By element, By locator) {
        return map2.get(relatively).apply(element, locator, lDriver);
    }
}
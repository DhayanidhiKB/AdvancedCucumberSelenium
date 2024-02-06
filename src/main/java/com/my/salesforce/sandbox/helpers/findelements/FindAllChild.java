package com.my.salesforce.sandbox.helpers.findelements;

import com.my.salesforce.sandbox.enums.ByLocator;
import com.my.salesforce.sandbox.enums.Relatively;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class FindAllChild {
    private static final Map<ByLocator, BiFunction<WebElement, String, List<WebElement>>> map1 = new HashMap<>();
    private static final BiFunction<WebElement, String, List<WebElement>> Id = (p, locatorValue) -> p.findElements(By.id(locatorValue));
    private static final BiFunction<WebElement, String, List<WebElement>> Name = (p, locatorValue) -> p.findElements(By.name(locatorValue));
    private static final BiFunction<WebElement, String, List<WebElement>> ClassName = (p, locatorValue) -> p.findElements(By.className(locatorValue));
    private static final BiFunction<WebElement, String, List<WebElement>> LinkText = (p, locatorValue) -> p.findElements(By.linkText(locatorValue));
    private static final BiFunction<WebElement, String, List<WebElement>> PartialLinkText = (p, locatorValue) -> p.findElements(By.partialLinkText(locatorValue));
    private static final BiFunction<WebElement, String, List<WebElement>> TagName = (p, locatorValue) -> p.findElements(By.tagName(locatorValue));
    private static final BiFunction<WebElement, String, List<WebElement>> Xpath = (p, locatorValue) -> p.findElements(By.xpath(locatorValue));
    private static final BiFunction<WebElement, String, List<WebElement>> Css = (p, locatorValue) -> p.findElements(By.cssSelector(locatorValue));

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

    public List<WebElement> element(@NotNull ByLocator byLocator, WebElement parent, String locatorValue) {
        return map1.get(byLocator).apply(parent, locatorValue);
    }

    private static final Map<Relatively, TriFunction<WebElement, By, By, List<WebElement>>> map2 = new HashMap<>();
    private static final TriFunction<WebElement, By, By, List<WebElement>> Above = (p, locator, e) -> p.findElements(with(locator).above(e));
    private static final TriFunction<WebElement, By, By, List<WebElement>> Below = (p, locator, e) -> p.findElements(with(locator).below(e));
    private static final TriFunction<WebElement, By, By, List<WebElement>> Near = (p, locator, e) -> p.findElements(with(locator).near(e));
    private static final TriFunction<WebElement, By, By, List<WebElement>> RightOf = (p, locator, e) -> p.findElements(with(locator).toRightOf(e));
    private static final TriFunction<WebElement, By, By, List<WebElement>> LeftOf = (p, locator, e) -> p.findElements(with(locator).toLeftOf(e));

    static {
        map2.put(Relatively.above, Above);
        map2.put(Relatively.below, Below);
        map2.put(Relatively.near, Near);
        map2.put(Relatively.rightOf, RightOf);
        map2.put(Relatively.leftOf, LeftOf);
    }

    public List<WebElement> element(@NotNull Relatively relatively, WebElement parent, By locator, By element) {
        return map2.get(relatively).apply(parent, locator, element);
    }
}
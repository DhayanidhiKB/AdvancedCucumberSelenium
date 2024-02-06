package com.my.salesforce.sandbox.helpers.actions;

import com.my.salesforce.sandbox.enums.Condition;
import com.my.salesforce.sandbox.properties.UserConfig;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class Wait {
    private final WebDriver lDriver;
    private final WebDriverWait lWait;

    public Wait(WebDriver driver) {
        this.lDriver = driver;
        this.lWait = new WebDriverWait(driver, Duration.ofSeconds(UserConfig.getProperties().timeOut()));
    }

    private static final Map<Condition, BiConsumer<Integer, WebDriver>> map1 = new HashMap<>();
    private static final BiConsumer<Integer, WebDriver> implicitly = (secs, d) -> d.manage().timeouts().implicitlyWait(Duration.ofSeconds(secs));
    private static final BiConsumer<Integer, WebDriver> pageLoadTimeout = (secs, d) -> d.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(secs));
    private static final BiConsumer<Integer, WebDriver> scriptTimeout = (secs, d) -> d.manage().timeouts().scriptTimeout(Duration.ofSeconds(secs));

    static {
        map1.put(Condition.implicitlyFor, implicitly);
        map1.put(Condition.pageLoadTimeout, pageLoadTimeout);
        map1.put(Condition.scriptTimeout, scriptTimeout);
    }

    public void until(@NotNull Condition condition, int seconds) {
        map1.get(condition).accept(seconds, lDriver);
    }

    public void until(@NotNull Condition condition) {
        if (condition.equals(Condition.alertIsPresent)) {
            lWait.until(ExpectedConditions.alertIsPresent());
        }
    }

    private static final Map<Condition, BiConsumer<WebElement, WebDriverWait>> map2 = new HashMap<>();
    private static final BiConsumer<WebElement, WebDriverWait> VisibilityOf = (e, w) -> w.until(ExpectedConditions.visibilityOf(e));
    private static final BiConsumer<WebElement, WebDriverWait> InvisibilityOf = (e, w) -> w.until(ExpectedConditions.invisibilityOf(e));
    private static final BiConsumer<WebElement, WebDriverWait> Clickable = (e, w) -> w.until(ExpectedConditions.elementToBeClickable(e));
    private static final BiConsumer<WebElement, WebDriverWait> Selectable = (e, w) -> w.until(ExpectedConditions.elementToBeSelected(e));
    private static final BiConsumer<WebElement, WebDriverWait> Staleness = (e, w) -> w.until(ExpectedConditions.stalenessOf(e));
    private static final BiConsumer<WebElement, WebDriverWait> Frame = (e, w) -> w.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(e));

    static {
        map2.put(Condition.visibilityOf, VisibilityOf);
        map2.put(Condition.invisibilityOf, InvisibilityOf);
        map2.put(Condition.clickable, Clickable);
        map2.put(Condition.selectable, Selectable);
        map2.put(Condition.staleness, Staleness);
        map2.put(Condition.frame, Frame);
    }

    public void until(@NotNull Condition condition, WebElement element) {
        map2.get(condition).accept(element, this.lWait);
    }

    private static final Map<Condition, BiConsumer<By, WebDriverWait>> map3 = new HashMap<>();
    private static final BiConsumer<By, WebDriverWait> presenceOf = (by, w) -> w.until(ExpectedConditions.presenceOfElementLocated(by));
    private static final BiConsumer<By, WebDriverWait> visibilityOf = (by, w) -> w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    private static final BiConsumer<By, WebDriverWait> invisibilityOf = (by, w) -> w.until(ExpectedConditions.invisibilityOfElementLocated(by));
    private static final BiConsumer<By, WebDriverWait> clickable = (by, w) -> w.until(ExpectedConditions.elementToBeClickable(by));
    private static final BiConsumer<By, WebDriverWait> selectable = (by, w) -> w.until(ExpectedConditions.elementToBeSelected(by));
    private static final BiConsumer<By, WebDriverWait> frame = (by, w) -> w.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));

    static {
        map3.put(Condition.presenceOf, presenceOf);
        map3.put(Condition.visibilityOf, visibilityOf);
        map3.put(Condition.invisibilityOf, invisibilityOf);
        map3.put(Condition.clickable, clickable);
        map3.put(Condition.staleness, selectable);
        map3.put(Condition.frame, frame);
    }

    public void until(@NotNull Condition condition, By locator) {
        map3.get(condition).accept(locator, this.lWait);
    }

    private static final Map<Condition, BiConsumer<String, WebDriverWait>> map4 = new HashMap<>();
    private static final BiConsumer<String, WebDriverWait> titleIs = (s, w) -> w.until(ExpectedConditions.titleIs(s));
    private static final BiConsumer<String, WebDriverWait> titleContains = (s, w) -> w.until(ExpectedConditions.titleContains(s));
    private static final BiConsumer<String, WebDriverWait> urlToBe = (s, w) -> w.until(ExpectedConditions.urlToBe(s));
    private static final BiConsumer<String, WebDriverWait> urlMatches = (s, w) -> w.until(ExpectedConditions.urlMatches(s));
    private static final BiConsumer<String, WebDriverWait> urlContains = (s, w) -> w.until(ExpectedConditions.urlContains(s));

    static {
        map4.put(Condition.titleIs, titleIs);
        map4.put(Condition.titleContains, titleContains);
        map4.put(Condition.urlToBe, urlToBe);
        map4.put(Condition.urlMatches, urlMatches);
        map4.put(Condition.urlContains, urlContains);
    }

    public void until(@NotNull Condition condition, String text) {
        map4.get(condition).accept(text, this.lWait);
    }

    public void untilNumberOfWindowsToBe(int noOfWindows) {
        this.lWait.until(ExpectedConditions.numberOfWindowsToBe(noOfWindows));
    }
}
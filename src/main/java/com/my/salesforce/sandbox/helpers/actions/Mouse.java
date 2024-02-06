package com.my.salesforce.sandbox.helpers.actions;

import com.my.salesforce.sandbox.enums.*;
import lombok.NonNull;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Mouse {
    private final WebDriver lDriver;
    private final Wait wait;
    private final Javascript js;

    public Mouse(WebDriver driver) {
        this.lDriver = driver;
        this.wait = new Wait(this.lDriver);
        this.js = new Javascript(this.lDriver);
    }

    public void click(@NonNull WebElement element) {
        wait.until(Condition.clickable, element);
        js.executor(ExecuteScript.highlight, element);
        new Actions(this.lDriver).click(element).perform();
    }

    public void clickAndHold(@NonNull WebElement element) {
        wait.until(Condition.clickable, element);
        new Actions(this.lDriver).clickAndHold(element).perform();
    }

    public void release(@NonNull WebElement element) {
        wait.until(Condition.clickable, element);
        new Actions(this.lDriver).release(element).perform();
    }

    public void rightClick(@NonNull WebElement element) {
        wait.until(Condition.clickable, element);
        new Actions(this.lDriver).contextClick(element).perform();
    }

    public void doubleClick(@NonNull WebElement element) {
        wait.until(Condition.clickable, element);
        new Actions(this.lDriver).doubleClick(element).perform();
    }

    public void moveOver(@NonNull WebElement element) {
        wait.until(Condition.clickable, element);
        new Actions(this.lDriver).moveToElement(element).perform();
    }

    public void moveOverAndClickOn(@NonNull WebElement element) {
        wait.until(Condition.clickable, element);
        new Actions(this.lDriver).moveToElement(element).pause(Duration.ofSeconds(1))
                .click(element).pause(Duration.ofSeconds(3)).build().perform();
    }

    public void type(String text, @NonNull WebElement element) {
        wait.until(Condition.clickable, element);
        new Actions(this.lDriver).click(element).sendKeys(element, Keys.chord(text))
                .pause(Duration.ofSeconds(3)).build().perform();
    }

    public void type(String keys) {
        new Actions(this.lDriver).sendKeys(keys).perform();
    }

    public void dragAndDrop(@NonNull WebElement source, @NonNull WebElement destination) {
        wait.until(Condition.clickable, source);
        wait.until(Condition.clickable, destination);
        new Actions(this.lDriver).dragAndDrop(source, destination).perform();
    }
}
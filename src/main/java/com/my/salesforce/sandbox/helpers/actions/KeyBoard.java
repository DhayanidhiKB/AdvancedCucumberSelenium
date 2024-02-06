package com.my.salesforce.sandbox.helpers.actions;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.enums.Condition;
import lombok.NonNull;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class KeyBoard {
    private final WebDriver lDriver;
    private final Wait wait;

    public KeyBoard(WebDriver driver) {
        this.lDriver = driver;
        this.wait = new Wait(this.lDriver);
    }

    public void type(String keys, @NonNull WebElement element) {
        wait.until(Condition.clickable, element);
        new Actions(this.lDriver).click(element).sendKeys(Keys.chord(keys)).build().perform();
    }

    public void upperCase(String text) {
        new Actions(this.lDriver).keyDown(Keys.SHIFT).keyDown(Keys.chord(text)).build().perform();
    }

    public void selectAll() {
        new Actions(this.lDriver).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
    }

    public void cut() {
        new Actions(this.lDriver).keyDown(Keys.CONTROL).sendKeys("x").keyUp(Keys.CONTROL).build().perform();
    }

    public void copy() {
        new Actions(this.lDriver).clickAndHold().keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();
    }

    public void paste() {
        new Actions(this.lDriver).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
    }

    public void press(Keys key) {
        new Actions(this.lDriver).sendKeys(key).perform();
    }

    public void typeAndSelect(String value) {
        new Actions(this.lDriver).sendKeys(Keys.chord(value)).pause(Duration.ofSeconds(2))
                .sendKeys(Keys.RETURN).build().perform();
    }

    public void oneKeyDownAndEnter() {
        new Actions(this.lDriver).pause(Duration.ofSeconds(7)).sendKeys(Keys.ARROW_DOWN)
                .pause(Duration.ofSeconds(7)).sendKeys(Keys.RETURN).build().perform();
    }

    public void twoKeyDownAndEnter() {
        new Actions(this.lDriver).pause(Duration.ofSeconds(7)).sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN).pause(Duration.ofSeconds(7)).sendKeys(Keys.RETURN).build().perform();
    }

    public void uploadFile(String filePath) throws AWTException {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        StringSelection path = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path, null);

        Robot robot = new Robot();
        robot.delay(250);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(50);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
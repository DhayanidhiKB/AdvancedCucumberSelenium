package com.my.salesforce.sandbox.helpers.actions;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.my.salesforce.sandbox.properties.UserConfig;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.my.salesforce.sandbox.Constants.*;

public class Snap {
    private final WebDriver lDriver;

    private final String snapShotPath = ROOT_DIRECTORY + UserConfig.getProperties().snaps();

    public Snap(WebDriver driver) {
        this.lDriver = driver;
    }

    private File sourceImage;
    private File destinationImage;

    public String viewableArea(String fileName) throws IOException {
        this.sourceImage = ((TakesScreenshot) this.lDriver).getScreenshotAs(OutputType.FILE);
        this.destinationImage = new File(UserConfig.getProperties().snaps() + fileName);
        FileHandler.copy(this.sourceImage, this.destinationImage);
        return this.snapShotPath + fileName;
    }

    public String element(@NotNull WebElement element, String fileName) throws IOException {
        this.sourceImage = element.getScreenshotAs(OutputType.FILE);
        this.destinationImage = new File(UserConfig.getProperties().snaps() + fileName);
        FileHandler.copy(this.sourceImage, this.destinationImage);
        return this.snapShotPath + fileName;
    }

    public String fullPage(String fileName) throws IOException {
        this.sourceImage = ((FirefoxDriver) this.lDriver).getFullPageScreenshotAs(OutputType.FILE);
        this.destinationImage = new File(UserConfig.getProperties().snaps() + fileName);
        FileHandler.copy(this.sourceImage, this.destinationImage);
        return this.snapShotPath + fileName;
    }

    public String shutterViewableArea(String fileName) {
        Shutterbug.shootPage(this.lDriver, Capture.VIEWPORT).withTitle(new Date().toString()).withName(fileName).save(this.snapShotPath);
        return this.snapShotPath + fileName;
    }

    public String shutterFullPage(String fileName) {
        Shutterbug.shootPage(this.lDriver, Capture.FULL_SCROLL).withTitle(new Date().toString()).withName(fileName).save(this.snapShotPath);
        return this.snapShotPath + fileName;
    }

    public String shutterElement(WebElement element, String fileName) {
        Shutterbug.shootElement(this.lDriver, element).withTitle(new Date().toString()).withName(fileName).save(this.snapShotPath);
        return this.snapShotPath + fileName;
    }

    public void compare(String imagePath, double deviation) throws IOException {
        Shutterbug.shootPage(this.lDriver).equals(imagePath, deviation);
    }

    public void compare(String imagePath, double deviation, WebElement element) throws IOException {
        Shutterbug.shootElement(this.lDriver, element).equals(imagePath, deviation);
    }
}
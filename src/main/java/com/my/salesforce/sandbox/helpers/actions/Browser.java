package com.my.salesforce.sandbox.helpers.actions;

import org.openqa.selenium.WebDriver;

public class Browser {
    private final WebDriver lDriver;

    public Browser(WebDriver driver) {
        this.lDriver = driver;
    }

    public void maximize() {
        this.lDriver.manage().window().maximize();
    }

    public void close() {
        this.lDriver.close();
    }

    public void closeAll() {
        this.lDriver.quit();
    }

    public void forward() {
        this.lDriver.navigate().forward();
    }

    public void backward() {
        this.lDriver.navigate().back();
    }

    public void refresh() {
        this.lDriver.navigate().refresh();
    }

    public void getTo(String url) {
        this.lDriver.get(url);
    }

    public void navigateTo(String url) {
        this.lDriver.navigate().to(url);
    }
}
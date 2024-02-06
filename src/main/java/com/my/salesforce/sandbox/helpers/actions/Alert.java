package com.my.salesforce.sandbox.helpers.actions;

import org.openqa.selenium.WebDriver;

public class Alert {
    private final WebDriver lDriver;

    public Alert(WebDriver driver) {
        this.lDriver = driver;
    }

    public void accept() {
        this.lDriver.switchTo().alert().accept();
    }

    public void dismiss() {
        this.lDriver.switchTo().alert().dismiss();
    }

    public String getText() {
        return this.lDriver.switchTo().alert().getText().trim();
    }

    public void type(String text) {
        this.lDriver.switchTo().alert().sendKeys(text);
    }
}
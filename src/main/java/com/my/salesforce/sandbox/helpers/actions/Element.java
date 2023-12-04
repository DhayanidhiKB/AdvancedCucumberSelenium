package com.my.salesforce.sandbox.helpers.actions;

import lombok.NonNull;
import org.openqa.selenium.WebElement;

public class Element {
    public void clear(@NonNull WebElement element) {
        element.clear();
    }

    public void click(@NonNull WebElement element) {
        element.click();
    }

    public void type(String text, @NonNull WebElement element) {
        element.sendKeys(text);
    }

    public void submit(@NonNull WebElement element) {
        element.submit();
    }
}
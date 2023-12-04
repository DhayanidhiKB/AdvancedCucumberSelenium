package com.my.salesforce.sandbox.helpers.actions;

import com.my.salesforce.sandbox.enums.Option;
import com.my.salesforce.sandbox.helpers.findelements.Find;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class Choose {
    private final WebDriver lDriver;
    private static Find find;

    public Choose(WebDriver driver) {
        this.lDriver = driver;
        find = new Find(driver);
    }

    private static final Map<Option, BiConsumer<String, By>> map1 = new HashMap<>();
    private static final BiConsumer<String, By> ByText = (s, locator) -> {
        Select dropDown = new Select(find.element(locator));
        dropDown.selectByVisibleText(s);
    };
    private static final BiConsumer<String, By> ByValue = (s, locator) -> {
        Select dropDown = new Select(find.element(locator));
        dropDown.selectByValue(s);
    };
    private static final BiConsumer<String, By> ByIndex = (s, locator) -> {
        Select dropDown = new Select(find.element(locator));
        dropDown.selectByIndex(Integer.parseInt(s));
    };

    static {
        map1.put(Option.byText, ByText);
        map1.put(Option.byValue, ByValue);
        map1.put(Option.byIndex, ByIndex);
    }

    public void the(@NotNull Option condition, String value, By locator) {
        map1.get(condition).accept(value, locator);
    }

    private static final Map<Option, BiConsumer<String, WebElement>> map2 = new HashMap<>();
    private static final BiConsumer<String, WebElement> Text = (s, e) -> {
        Select dropDown = new Select(e);
        dropDown.selectByVisibleText(s);
    };
    private static final BiConsumer<String, WebElement> Value = (s, e) -> {
        Select dropDown = new Select(e);
        dropDown.selectByValue(s);
    };
    private static final BiConsumer<String, WebElement> Index = (s, e) -> {
        Select dropDown = new Select(e);
        dropDown.selectByIndex(Integer.parseInt(s));
    };

    static {
        map2.put(Option.byText, Text);
        map2.put(Option.byValue, Value);
        map2.put(Option.byIndex, Index);
    }

    public void the(@NotNull Option condition, String value, WebElement element) {
        map2.get(condition).accept(value, element);
    }
}
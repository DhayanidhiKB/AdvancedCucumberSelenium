package com.my.salesforce.sandbox.helpers.actions;

import com.my.salesforce.sandbox.enums.ExecuteScript;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class Javascript {
    private final WebDriver lDriver;

    public Javascript(WebDriver driver) {
        this.lDriver = driver;
    }

    private static final Map<ExecuteScript, Consumer<WebDriver>> map1 = new HashMap<>();
    private static final Consumer<WebDriver> PageRefresh = d -> ((JavascriptExecutor) d).executeScript("history.go(0)");
    private static final Consumer<WebDriver> ScrollToBottom = d -> ((JavascriptExecutor) d).executeScript("window.scrollTo(0, document.body.scrollHeight || document.documentElement.scrollHeight)");
    private static final Consumer<WebDriver> ScrollToTop = d -> ((JavascriptExecutor) d).executeScript("window.scrollTo(0, 0)");

    static {
        map1.put(ExecuteScript.pageRefresh, PageRefresh);
        map1.put(ExecuteScript.scrollToBottom, ScrollToBottom);
        map1.put(ExecuteScript.scrollToTop, ScrollToTop);
    }

    public void executor(@NotNull ExecuteScript script) {
        map1.get(script).accept(this.lDriver);
    }

    private static final Map<ExecuteScript, Function<WebDriver, String>> map2 = new HashMap<>();
    private static final Function<WebDriver, String> GetTitle = d -> ((JavascriptExecutor) d).executeScript("return document.title;").toString().trim();
    private static final Function<WebDriver, String> GetUrl = d -> ((JavascriptExecutor) d).executeScript("return document.URL;").toString().trim();
    private static final Function<WebDriver, String> GetDomain = d -> ((JavascriptExecutor) d).executeScript("return document.domain;").toString().trim();
    private static final Function<WebDriver, String> GetInnerText = d -> ((JavascriptExecutor) d).executeScript("return document.documentElement.innerText;").toString().trim();

    static {
        map2.put(ExecuteScript.getTitle, GetTitle);
        map2.put(ExecuteScript.getUrl, GetUrl);
        map2.put(ExecuteScript.getDomain, GetDomain);
        map2.put(ExecuteScript.getInnerText, GetInnerText);
    }

    public String getExecutor(@NotNull ExecuteScript script) {
        return map2.get(script).apply(this.lDriver);
    }

    private static final Map<ExecuteScript, BiConsumer<WebElement, WebDriver>> map3 = new HashMap<>();
    private static final BiConsumer<WebElement, WebDriver> Clear = (e, d) -> ((JavascriptExecutor) d).executeScript("arguments[0].value='';", e);
    private static final BiConsumer<WebElement, WebDriver> Click = (e, d) -> ((JavascriptExecutor) d).executeScript("arguments[0].click()", e);
    private static final BiConsumer<WebElement, WebDriver> ScrollTo = (e, d) -> ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(true);", e);
    private static final BiConsumer<WebElement, WebDriver> Highlight = (e, d) -> ((JavascriptExecutor) d).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", e);

    static {
        map3.put(ExecuteScript.clear, Clear);
        map3.put(ExecuteScript.click, Click);
        map3.put(ExecuteScript.scrollTo, ScrollTo);
        map3.put(ExecuteScript.highlight, Highlight);
    }

    public void executor(@NotNull ExecuteScript script, WebElement element) {
        map3.get(script).accept(element, this.lDriver);
    }

    private static final Map<ExecuteScript, BiConsumer<Integer, WebDriver>> map4 = new HashMap<>();
    private static final BiConsumer<Integer, WebDriver> ScrollUp = (i, d) -> ((JavascriptExecutor) d).executeScript("window.scrollBy(0,-" + i + ")");
    private static final BiConsumer<Integer, WebDriver> ScrollDown = (i, d) -> ((JavascriptExecutor) d).executeScript("window.scrollBy(0," + i + ")");
    private static final BiConsumer<Integer, WebDriver> Sleep = (i, d) -> ((JavascriptExecutor) d).executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], " + i + ");");

    static {
        map4.put(ExecuteScript.scrollUp, ScrollUp);
        map4.put(ExecuteScript.scrollDown, ScrollDown);
        map4.put(ExecuteScript.sleep, Sleep);
    }

    public void executor(@NotNull ExecuteScript script, int value) {
        map4.get(script).accept(value, this.lDriver);
    }

    public void executor(@NotNull ExecuteScript script, String url) {
        if (script.equals(ExecuteScript.open)) {
            ((JavascriptExecutor) lDriver).executeScript("window.location = '" + url + "'");
        } else {
            throw new RuntimeException("Double check parameters: enum condition (or) string");
        }
    }

    public void executor(@NotNull ExecuteScript script, String text, WebElement element) {
        if (script.equals(ExecuteScript.type)) {
            ((JavascriptExecutor) lDriver).executeScript("arguments[0].value = '" + text + "'", element);
        } else {
            throw new RuntimeException("Double check parameters: enum condition (or) element (or) string");
        }
    }
}
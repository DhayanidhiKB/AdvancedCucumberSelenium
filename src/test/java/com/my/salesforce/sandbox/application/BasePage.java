package com.my.salesforce.sandbox.application;

import com.codeborne.selenide.*;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.helpers.actions.Browser;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public abstract class BasePage {
    protected WebDriver lDriver;
    protected Browser browser;

    public BasePage(WebDriver driver) {
        lDriver = driver;
        WebDriverRunner.setWebDriver(lDriver);
        browser = new Browser(lDriver);
    }

    public SelenideElement locate(@NotNull String locator, String wildCard) {
        return $(byXpath(locator.replace("%value%", wildCard)));
    }

    public SelenideElement locate(@NotNull String locator, String wildCard1, String wildCard2) {
        return $(byXpath(locator.replace("%value1%", wildCard1).replace("%value2%", wildCard2)));
    }

    public ElementsCollection locateElements(@NotNull String locator, String wildCard) {
        return $$(byXpath(locator.replace("%value%", wildCard)));
    }

    public SelenideElement isItVisible(@NotNull SelenideElement element) {
        element.shouldBe(visible).shouldHave(appear);
        return element;
    }

    public SelenideElement isItInteractable(@NotNull SelenideElement element) {
        element.shouldBe(visible).shouldBe(enabled).shouldBe(interactable)
                .shouldHave(appear).shouldHave(interactable);
        return element;
    }

    public void clear(@NotNull SelenideElement element) {
        element.shouldBe(visible).shouldBe(enabled).shouldBe(interactable)
                .shouldHave(appear).shouldHave(interactable).clear();
    }

    public void clickOn(@NotNull SelenideElement element) {
        element.shouldBe(visible).shouldBe(enabled).shouldBe(interactable)
                .shouldHave(appear).shouldHave(interactable).click();
    }

    public SelenideElement setValue(String value, @NotNull SelenideElement element) {
        element.shouldBe(visible).shouldBe(enabled).shouldBe(interactable)
                .shouldHave(appear).shouldHave(interactable).clear();
        element.setValue(value);
        return element;
    }

    public void type(String value, @NotNull SelenideElement element) {
        element.shouldBe(visible).shouldBe(enabled).shouldBe(interactable)
                .shouldHave(appear).shouldHave(interactable).clear();
        element.type(value);
    }

    public void shouldHaveText(@NotNull SelenideElement element, String text) {
        element.shouldBe(visible).shouldHave(appear).shouldHave(text(text));
    }

    public void mouseOverAndClickOn(@NotNull SelenideElement element) {
        actions().pause(Duration.ofSeconds(1)).moveToElement(element)
                .pause(Duration.ofSeconds(1)).click(element)
                .pause(Duration.ofSeconds(1)).build().perform();
    }

    public void mouseOverAndSelect(String text, @NotNull SelenideElement element) {
        actions().pause(Duration.ofSeconds(2)).moveToElement(element).click(element)
                .pause(Duration.ofSeconds(2)).sendKeys(text)
                .pause(Duration.ofSeconds(1)).sendKeys(Keys.ENTER).build().perform();
    }

    public void switch_to_frame(@NotNull SelenideElement element) {
        isItVisible(element);
        Selenide.switchTo().frame(element);
    }

    public void page_refresh_and_wait() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(30));
        Selenide.refresh();
    }
}
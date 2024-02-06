package com.my.salesforce.sandbox.application.pages.welcome;

import com.codeborne.selenide.Selenide;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.welcome.HeaderElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class Header extends BasePage implements HeaderElements {
    public Header is_ready() {
        isItVisible(GlobalActions);
        isItInteractable(Search);
        isItInteractable(ProfileCard);
        return this;
    }

    public Header search(String resource) {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        clickOn(Search);
        type(resource, SearchInput);
        clickOn($(locate(SearchResult, resource)));
        return this;
    }

    public Header refresh() {
        Selenide.refresh();
        return this;
    }

    public void sign_off() {
        clickOn(ProfileCard);
        clickOn(LogOut);
    }

    private AppNavigator appNavigator;

    public Header(WebDriver driver) {
        super(driver);
        setAppNavigator(new AppNavigator(lDriver));
    }
}
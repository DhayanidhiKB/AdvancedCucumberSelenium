package com.my.salesforce.sandbox.application.pages.shopping_cart;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.CheckOutElements;
import com.my.salesforce.sandbox.application.pages.opportunities.details.edit_products.EditProducts;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class CheckOut extends BasePage implements CheckOutElements {
    public CheckOut is_ready() {
        switch_to_frame(InnerIFrame);
        isItVisible(Header);
        isItVisible(SubHeader);
        return this;
    }

    public void verify_error() {
        isItVisible(ErrorMessage);
        Selenide.switchTo().defaultContent().switchTo().frame(iFrame);
        clickOn(Close);
    }

    public CheckOut no_of_options(@NotNull String user) {
        switch (user) {
            case "Onboarding":
                NoOfOptions.shouldHave(CollectionCondition.size(4));
                break;
            case "Sales":
                NoOfOptions.shouldHave(CollectionCondition.size(3));
                break;
        }
        return this;
    }

    public CheckOut select(String option) {
        locate(Option, option).scrollIntoView(true);
        isItInteractable(locate(Option, option));
        executeJavaScript("arguments[0].click();", locate(Option, option));
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(50));
        return this;
    }

    public CheckOut switch_to_default_content() {
        Selenide.switchTo().defaultContent();
        return this;
    }

    private EditProducts editProducts;

    public CheckOut(WebDriver driver) {
        super(driver);
        setEditProducts(new EditProducts(lDriver));
    }
}
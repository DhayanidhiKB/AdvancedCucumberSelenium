package com.my.salesforce.sandbox.application.pages.shopping_cart.startrack;

import com.codeborne.selenide.Condition;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.startrack.StarTrackProductAttributesPageElements;
import com.my.salesforce.sandbox.application.pages.shopping_cart.eparcel.CartActions;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

@Getter
@Setter
public class StarTrackProductAttributesPage extends BasePage implements StarTrackProductAttributesPageElements {
    public StarTrackProductAttributesPage is_ready() {
        isItVisible(locate(TabItems, "Product Attributes"));
        isItVisible(locate(ListBox, "1"));
        isItVisible(locate(ListBox, "2"));
        return this;
    }

    public StarTrackProductAttributesPage enter_and_validate(@NotNull String productName, String serviceType,
                                                             String primarySuburb, String eligibilityTier, String suburb) {
        switch (productName) {
            case "Premium - STE":
                clickOn(locate(ListBox, "1"));
                clickOn(locate(Option, serviceType));
                break;
            case "Road Express - STE":
                locate(ListBox, "1").shouldHave(Condition.attribute("title", serviceType));
                break;
        }
        isItVisible(locate(PrimaryPostcodeSubUrb, primarySuburb));
        isItVisible(locate(Tier, eligibilityTier));
        isItInteractable(locate(PostcodeSubUrb, suburb));
        clickOn(Validate);
        return this;
    }

    public StarTrackProductAttributesPage refresh_pricing() {
        ProgressBar.shouldBe(hidden).shouldHave(hidden);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        clickOn(locate(Product, "Refresh Pricing"));
        return this;
    }

    public StarTrackProductAttributesPage checkOut(String productName) {
        isItInteractable(AddMoreProducts);
        isItInteractable(locate(ProductNameBlock, productName));
        isItInteractable(locate(Product, "Checkout"));
        ProgressBar.shouldBe(hidden).shouldHave(hidden);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        clickOn(locate(Product, "Checkout"));
        return this;
    }

    public StarTrackProductAttributesPage review() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(10));
        clickOn(ReviewCart);
        /*isItInteractable(ViewRateCard);
        isItInteractable(NoLPRequired);
        ProgressBar.shouldBe(disappear).shouldHave(disappear);*/
        return this;
    }

    public void configure_product() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        clickOn(ConfigureIcon);
    }

    private CartActions cartActions;

    public StarTrackProductAttributesPage(WebDriver driver) {
        super(driver);
        setCartActions(new CartActions(lDriver));
    }
}
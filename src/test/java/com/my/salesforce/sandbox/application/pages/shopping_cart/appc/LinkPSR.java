package com.my.salesforce.sandbox.application.pages.shopping_cart.appc;

import com.codeborne.selenide.Selenide;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.appc.APPCProductAttributesPageElements;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class LinkPSR extends BasePage implements APPCProductAttributesPageElements {
    public void link(@NotNull String condition, String quote_id) {
        if (condition.equals("with")) {
            $(PSRRequiredMessage).shouldBe(visible).shouldBe(enabled).shouldBe(interactable).shouldHave(appear);
            $(LinkPSR).click();
            getApplyPSRPage().is_it_ready().verify(quote_id).apply_psr().back_to_cart();
            $(iFrame).shouldBe(visible).shouldHave(appear);
            Selenide.switchTo().frame($(iFrame));
            $(ProductLink).shouldBe(visible).shouldBe(enabled).shouldBe(interactable).shouldHave(appear);
            if ($$(RefreshPricing).size() > 0) {
                $(ProgressBar).shouldBe(appear).shouldHave(appear);
                Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
                $(RefreshPricing).shouldBe(visible).shouldBe(enabled).shouldBe(interactable).shouldHave(appear).click();
                $(ProductLink).shouldBe(visible).shouldBe(enabled).shouldBe(interactable).shouldHave(appear);
                $(ProgressBar).shouldBe(disappear).shouldBe(hidden).shouldHave(disappear);
                Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
            }
            $(Checkout).shouldBe(visible).shouldBe(enabled).shouldBe(interactable).shouldHave(appear)
                    .shouldHave(interactable);
            $(PSRRequiredMessage).shouldBe(disappear).shouldHave(disappear);
        }
    }

    private ApplyPSRPage applyPSRPage;

    public LinkPSR(WebDriver driver) {
        super(driver);
        setApplyPSRPage(new ApplyPSRPage(lDriver));
    }
}
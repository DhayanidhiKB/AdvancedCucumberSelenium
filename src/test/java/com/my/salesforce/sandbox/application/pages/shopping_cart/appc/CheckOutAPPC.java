package com.my.salesforce.sandbox.application.pages.shopping_cart.appc;

import com.codeborne.selenide.Selenide;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.appc.APPCProductAttributesPageElements;
import com.my.salesforce.sandbox.application.pages.opportunities.details.edit_products.EditProducts;
import com.my.salesforce.sandbox.application.pages.shopping_cart.CheckOut;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class CheckOutAPPC extends BasePage implements APPCProductAttributesPageElements {
    public CheckOutAPPC is_ready() {
        isItInteractable(MoreMenu);
        isItInteractable(AddMoreProducts);
        isItInteractable(HeaderCheckBox);
        isItInteractable(ProductLink);
        isItInteractable(ConfigureIcon);
        isItInteractable(LinkPSR);
        return this;
    }

    public CheckOutAPPC please(String action, String reason, String comment) {
        isItInteractable(locate(Product, "Checkout"));
        ProgressBar.shouldBe(disappear).shouldBe(hidden).shouldHave(disappear);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        clickOn(MoreMenu);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        clickOn(locate(Product, action));
        if (action.equals("Use Offline Rates and Checkout")) {
            getUseOfflineRates().is_ready().fill(reason, comment).next();
        }
        Selenide.switchTo().defaultContent();
        return this;
    }

    public void configure_product() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        clickOn(ConfigureIcon);
    }

    public CheckOutAPPC check_out() {
        isItInteractable(Checkout);
        ProgressBar.shouldBe(disappear).shouldBe(hidden).shouldHave(disappear);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        clickOn(Checkout);
        return this;
    }

    public CheckOutAPPC check_out(@NotNull String condition, String quote_id) {
        getLinkPSR().link(condition, quote_id);
        isItInteractable(ViewRateCard);
        isItInteractable(Checkout);
        ProgressBar.shouldBe(disappear).shouldHave(disappear);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        clickOn(Checkout);
        return this;
    }

    public CheckOutAPPC choose(String user, String option) {
        getCheckOut().is_ready().no_of_options(user).select(option);
        return this;
    }

    public CheckOutAPPC switch_to_default_content() {
        Selenide.switchTo().defaultContent();
        return this;
    }

    private CheckOut checkOut;
    private LinkPSR linkPSR;
    private UseOfflineRates useOfflineRates;
    private EditProducts editProducts;

    public CheckOutAPPC(WebDriver driver) {
        super(driver);
        setCheckOut(new CheckOut(lDriver));
        setLinkPSR(new LinkPSR(lDriver));
        setUseOfflineRates(new UseOfflineRates(lDriver));
        setEditProducts(new EditProducts(lDriver));
    }
}
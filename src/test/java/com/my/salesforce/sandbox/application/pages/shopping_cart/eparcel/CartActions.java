package com.my.salesforce.sandbox.application.pages.shopping_cart.eparcel;

import com.codeborne.selenide.Selenide;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.eparcel.EParcelProductAttributesPageElements;
import com.my.salesforce.sandbox.application.pages.opportunities.details.edit_products.EditProducts;
import com.my.salesforce.sandbox.application.pages.shopping_cart.CheckOut;
import com.my.salesforce.sandbox.application.pages.shopping_cart.appc.UseOfflineRates;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class CartActions extends BasePage implements EParcelProductAttributesPageElements {
    public void add_more_products() {
        isItInteractable(ReviewCart);
        isItInteractable(Validate);
        isItInteractable(AddMoreProducts);
        actions().pause(Duration.ofSeconds(15)).click($(AddMoreProducts)).build().perform();
        Selenide.switchTo().defaultContent();
    }

    public CartActions check_out() {
        ProgressBar.shouldBe(disappear).shouldHave(disappear);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        if (locate(Product, "Refresh Pricing").is(visible) == true) {
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
            clickOn(locate(Product, "Refresh Pricing"));
        }
        ProgressBar.shouldBe(disappear).shouldHave(disappear);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        isItInteractable(locate(Product, "Checkout"));
        clickOn(locate(Product, "Checkout"));
        return this;
    }

    public CartActions please(String action, String reason, String comment) {
        ProgressBar.shouldBe(disappear).shouldHave(disappear);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        if (locate(Product, "Refresh Pricing").is(visible) == true) {
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
            locate(Product, "Refresh Pricing").click();
        }
        ProgressBar.shouldBe(disappear).shouldBe(hidden).shouldHave(disappear);
        clickOn(MoreMenu);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        //clickOn(locate(Product, action));
        if (action.equals("Use Offline Rates and Checkout")) {
            getUseOfflineRates().is_ready().fill(reason, comment).next();
        }
        Selenide.switchTo().defaultContent();
        return this;
    }

    public CartActions delete(String productName) {
        getDeleteProduct().deleteTheProduct(productName);
        return this;
    }

    public CartActions toggle_and_check_out() {
        ProgressBar.shouldBe(visible).shouldHave(appear);
        isItInteractable(locate(Product, "Refresh Pricing"));
        //ProgressBar.shouldBe(disappear).shouldHave(disappear);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        clickOn(MoreMenu);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        clickOn(locate(Product, "Checkout"));
        return this;
    }

    private CheckOut checkOut;
    private UseOfflineRates useOfflineRates;
    private EditProducts editProducts;
    private DeleteProduct deleteProduct;

    public CartActions(WebDriver driver) {
        super(driver);
        setCheckOut(new CheckOut(lDriver));
        setUseOfflineRates(new UseOfflineRates(lDriver));
        setEditProducts(new EditProducts(lDriver));
        setDeleteProduct(new DeleteProduct(lDriver));
    }
}
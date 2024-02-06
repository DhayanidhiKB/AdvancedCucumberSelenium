package com.my.salesforce.sandbox.application.pages.shopping_cart.eparcel;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.eparcel.EParcelProductAttributesPageElements;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

@Getter
@Setter
public class EParcelProductAttributesPage extends BasePage implements EParcelProductAttributesPageElements {
    public EParcelProductAttributesPage is_ready() {
        isItVisible(locate(TabItems, "Product Attributes"));
        isItInteractable(locate(ListBox, "1"));
        isItInteractable(locate(ListBox, "2"));
        return this;
    }

    public EParcelProductAttributesPage enter_and_validate(@NotNull String productName, String categoryOrIndustry,
                                                           String priceOrCustomer, String postcodeOrRevenue,
                                                           String amountOrPlatforms) {
        getValidateAttributes().validate(productName, categoryOrIndustry, priceOrCustomer,
                postcodeOrRevenue, amountOrPlatforms);
        return this;
    }

    public EParcelProductAttributesPage review_cart() {
        isItInteractable(AddMoreProducts);
        clickOn(ReviewCart);
        isItInteractable(MoreMenu);
        return this;
    }

    public EParcelProductAttributesPage review_cart_for(String productName) {
        isItInteractable(AddMoreProducts);
        isItInteractable(locate(ProductNameBlock, productName));
        return this;
    }

    public EParcelProductAttributesPage add_lodgementPoint(String productName, String lodgementPoint) {
        clickOn(locate(AddLP, productName));
        getManageLodgementPointsPage().is_ready().add_lodgement_point(lodgementPoint);
        return this;
    }

    public EParcelProductAttributesPage back_to_shopping_cart(String productName) {
        isItInteractable(locate(ProductNameBlock, productName));
        isItInteractable(MoreMenu);
        return this;
    }

    public EParcelProductAttributesPage refresh_pricing() {
        ProgressBar.shouldBe(disappear).shouldBe(hidden).shouldHave(hidden);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        clickOn(locate(Product, "Refresh Pricing"));
        return this;
    }

    public EParcelProductAttributesPage enter_rk_code(String rk_code) {
        clickOn(enterRKCode);
        getRateCardKey().is_ready().enter(rk_code);
        return this;
    }

    public EParcelProductAttributesPage submit_for_approval(String productName) {
        isItInteractable(locate(ProductNameBlock, productName));
        clickOn(locate(CheckBox, productName));
        clickOn(SubmitForApproval);
        getSubmitForApproval().is_ready().submit("Testing").return_to_cart();
        return this;
    }

    public void configure_product(String productName) {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        clickOn(locate(ConfigureIcon, productName));
    }

    public EParcelProductAttributesPage delete(String productName) {
        getDeleteProduct().deleteTheProduct(productName);
        return this;
    }

    private ValidateAttributes validateAttributes;
    private CartActions cartActions;
    private ManageLodgementPointsPage manageLodgementPointsPage;
    private RateCardKey rateCardKey;
    private SubmitForApproval submitForApproval;
    private DeleteProduct deleteProduct;

    public EParcelProductAttributesPage(WebDriver driver) {
        super(driver);
        setValidateAttributes(new ValidateAttributes(lDriver));
        setCartActions(new CartActions(lDriver));
        setManageLodgementPointsPage(new ManageLodgementPointsPage(lDriver));
        setRateCardKey(new RateCardKey(lDriver));
        setSubmitForApproval(new SubmitForApproval(lDriver));
        setDeleteProduct(new DeleteProduct(lDriver));
    }
}
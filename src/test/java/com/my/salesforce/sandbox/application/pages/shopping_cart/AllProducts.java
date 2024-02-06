package com.my.salesforce.sandbox.application.pages.shopping_cart;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.AllProductsElements;
import com.my.salesforce.sandbox.application.pages.opportunities.details.edit_products.EditProducts;
import com.my.salesforce.sandbox.application.pages.shopping_cart.appc.APPCProductAttributesPage;
import com.my.salesforce.sandbox.application.pages.shopping_cart.eparcel.EParcelProductAttributesPage;
import com.my.salesforce.sandbox.application.pages.shopping_cart.startrack.StarTrackProductAttributesPage;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class AllProducts extends BasePage implements AllProductsElements {
    public AllProducts is_ready() {
        switch_to_frame(iFrame);
        isItInteractable(Like);
        isItInteractable(Cart);
        isItInteractable(ReviewCart);
        isItInteractable(AbandonCart);
        isItInteractable(SortBy);
        isItInteractable(locate(ProductLink, "eParcel"));
        isItInteractable(locate(CheckBox, "eParcel"));
        return this;
    }

    public AllProducts switch_to_frame() {
        switch_to_frame(iFrame);
        return this;
    }

    public AllProducts add_to_cart(String productName) {
        setValue(productName, FindProducts).pressEnter();
        isItInteractable(locate(ProductLink, productName));
        clickOn(locate(CheckBox, productName));
        clickOn(AddToCart);
        return this;
    }

    public AllProducts configure(String productName) {
        actions().moveToElement($(Cart)).click()
                .moveToElement(locate(ConfigureProduct, productName)).click().build().perform();
        return this;
    }

    public AllProducts review() {
        clickOn(ReviewCart);
        return this;
    }

    public AllProducts still_not_configured(@NotNull String productName) {
        clickOn(ReviewCart);
        if (productName.contains("Parcel Contract")) {
            getAppcProductAttributesPage().getCheckOutAPPC()
                    .is_ready().check_out()
                    .getCheckOut()
                    .is_ready().verify_error();
            getAppcProductAttributesPage().getCheckOutAPPC().configure_product();
        } else if (productName.contains("eParcel") || productName.contains("International")) {
            getEParcelProductAttributesPage().review_cart_for(productName)
                    .getCartActions().check_out()
                    .getCheckOut().is_ready().verify_error();
            getEParcelProductAttributesPage().configure_product(productName);
        } else if (productName.contains("STE")) {
            getStarTrackProductAttributesPage().checkOut(productName)
                    .getCartActions().getCheckOut().is_ready().verify_error();
            getStarTrackProductAttributesPage().configure_product();
        }
        return this;
    }

    private APPCProductAttributesPage appcProductAttributesPage;
    private StarTrackProductAttributesPage starTrackProductAttributesPage;
    private EParcelProductAttributesPage eParcelProductAttributesPage;
    private EditProducts editProducts;

    public AllProducts(WebDriver driver) {
        super(driver);
        setAppcProductAttributesPage(new APPCProductAttributesPage(lDriver));
        setStarTrackProductAttributesPage(new StarTrackProductAttributesPage(lDriver));
        setEParcelProductAttributesPage(new EParcelProductAttributesPage(lDriver));
        setEditProducts(new EditProducts(lDriver));
    }
}
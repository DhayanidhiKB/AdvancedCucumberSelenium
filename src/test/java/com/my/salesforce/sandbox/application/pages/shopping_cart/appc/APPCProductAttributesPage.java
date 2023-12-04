package com.my.salesforce.sandbox.application.pages.shopping_cart.appc;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.shopping_cart.appc.APPCProductAttributesPageElements;
import lombok.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class APPCProductAttributesPage extends BasePage implements APPCProductAttributesPageElements {
    public APPCProductAttributesPage is_ready() {
        isItVisible(Header);
        isItInteractable(SubHeader);
        isItInteractable(EvaluatedSpend);
        return this;
    }

    public APPCProductAttributesPage validate_default_attributes(String price_structure, String lodgement_zone,
                                                                 String lodgement_zone1, String lodgement_zone2,
                                                                 String cubic_status, String cubic_factor) {
        isItVisible(locate(DefaultAttribute, price_structure));
        isItVisible(locate(DefaultAttribute, lodgement_zone));
        isItVisible(locate(DefaultAttribute, lodgement_zone1));
        isItVisible(locate(DefaultAttribute, lodgement_zone2));
        isItVisible(locate(CubicStatus, cubic_status));
        isItVisible(locate(DefaultAttribute, cubic_factor));
        return this;
    }

    public APPCProductAttributesPage enter_evaluated_spend(String evaluated_spend) {
        setValue(evaluated_spend, EvaluatedSpend);
        return this;
    }

    public APPCProductAttributesPage enter_transit_cover(String transit_cover) {
        isItInteractable(TransitCoverAmount).scrollIntoView(true);
        actions().pause(Duration.ofSeconds(1)).click($(TransitCoverAmount)).sendKeys(Keys.BACK_SPACE)
                .sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE)
                .sendKeys(transit_cover).pause(Duration.ofSeconds(3)).build().perform();
        return this;
    }

    public APPCProductAttributesPage validate(String tier, String transit_cover_type) {
        clickOn(Validate);
        isItVisible(locate(DefaultAttribute, tier));
        isItVisible(locate(DefaultAttribute, transit_cover_type));
        return this;
    }

    public APPCProductAttributesPage review_cart() {
        isItInteractable(ReviewCart);
        isItInteractable($(By.xpath("//button[@buttonid='id_task_right_customaction3']")));
        clickOn(ReviewCart);
        return this;
    }

    private CheckOutAPPC checkOutAPPC;

    public APPCProductAttributesPage(WebDriver driver) {
        super(driver);
        setCheckOutAPPC(new CheckOutAPPC(lDriver));
    }
}
package com.my.salesforce.sandbox.application.pages.opportunities.details;

import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.details.ProductsAndContractsPageElements;
import com.my.salesforce.sandbox.application.pages.opportunities.details.edit_products.EditProducts;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class ProductsAndContracts extends BasePage implements ProductsAndContractsPageElements {
    public ProductsAndContracts is_ready() {
        isItVisible(Header);
        isItInteractable(AddProducts);
        isItInteractable(SortProducts);
        return this;
    }

    public ProductsAndContracts edit_products() {
        clickOn(ShowMore);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        clickOn(BulkEdit);
        return this;
    }

    public String get_proposal_name() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        isItInteractable(Header).scrollIntoView(true);
        return isItInteractable(ProposalName).shouldHave(interactable).getText();
    }

    public void open_proposal(int index) {
        $(Header).scrollIntoView(true);
        executeJavaScript("window.scrollBy(0,100)");
        clickOn($$(Proposal).get(index));
    }

    public void open_contract(int index) {
        isItInteractable(locate(ViewAllLink, "Products")).scrollIntoView(true);
        clickOn($$(ApttusContract).get(index));
    }

    private EditProducts editProducts;

    public ProductsAndContracts(WebDriver driver) {
        super(driver);
        setEditProducts(new EditProducts(lDriver));
    }
}
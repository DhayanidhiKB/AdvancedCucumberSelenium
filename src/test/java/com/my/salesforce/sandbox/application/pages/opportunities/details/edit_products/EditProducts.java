package com.my.salesforce.sandbox.application.pages.opportunities.details.edit_products;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.details.edit_products.EditProductsElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class EditProducts extends BasePage implements EditProductsElements {
    public EditProducts is_ready() {
        isItVisible(Header);
        isItInteractable(ProductsLink);
        isItInteractable(OpportunitiesLink);
        isItInteractable(SelectAll);
        return this;
    }

    public EditProducts edit_quantity(String quantity) {
        getEdit().quantity().quantity(quantity);
        return this;
    }

    public EditProducts edit_unit_sales(String unit_sales) {
        getEdit().unit_sales(unit_sales);
        return this;
    }

    public EditProducts edit_start_date(String start_date) {
        getEdit().start_date().start_date(start_date);
        return this;
    }

    public EditProducts edit_end_date(String end_date) {
        getEdit().end_date().end_date(end_date);
        return this;
    }

    public EditProducts save() {
        getEdit().save();
        is_ready();
        return this;
    }

    public void back_to_opportunity(String opp_name) {
        clickOn(locate(Opportunity, opp_name));
    }

    public void confirm_opportunity_products() {
        isItInteractable(ProductCheckBox).click();
        isItInteractable(ConfirmOpportunityProducts).scrollIntoView(true).click();
    }

    private Edit edit;

    public EditProducts(WebDriver driver) {
        super(driver);
        setEdit(new Edit(lDriver));
    }
}
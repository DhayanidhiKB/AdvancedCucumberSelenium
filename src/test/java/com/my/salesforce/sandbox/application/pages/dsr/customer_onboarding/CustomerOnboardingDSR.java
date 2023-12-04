package com.my.salesforce.sandbox.application.pages.dsr.customer_onboarding;

import com.codeborne.selenide.CollectionCondition;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.CustomerOnboardingDSRPageElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class CustomerOnboardingDSR extends BasePage implements CustomerOnboardingDSRPageElements {
    public CustomerOnboardingDSR is_ready() {
        isItVisible(Header);
        isItInteractable(CustomerContact);
        return this;
    }

    public CustomerOnboardingDSR enter(String product, String description,
                                       String work_type, String support_work_type) {
        setValue("12345678", locate(TextField, "Billing Account Number"));
        $(CustomerContact).scrollIntoView(true);
        getProductListBox().choose(product);
        setValue(description, locate(TextArea, "Description"));
        locate(TextArea, "Description").scrollIntoView(true);
        getWorkTypeDropList().select(work_type, support_work_type);
        return this;
    }

    public CustomerOnboardingDSR save() {
        clickOn(Save);
        return this;
    }

    public CustomerOnboardingDSR validate_error() {
        List<String> mandatory_fields = Arrays.asList("Billing Account Number",
                "Product", "Work Type");
        ErrorLinks.shouldBe(CollectionCondition.size(3))
                .shouldHave(CollectionCondition.size(3));
        MandatoryFields.shouldHave(CollectionCondition.exactTexts(mandatory_fields));
        clickOn(ErrorIcon);
        return this;
    }

    private ProductListBox productListBox;
    private WorkTypeDropList workTypeDropList;

    public CustomerOnboardingDSR(WebDriver driver) {
        super(driver);
        setProductListBox(new ProductListBox(lDriver));
        setWorkTypeDropList(new WorkTypeDropList(lDriver));
    }
}
package com.my.salesforce.sandbox.application.pages.contract.dov;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.contract.dov.DOVLanguagesElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class DOVLanguages extends BasePage implements DOVLanguagesElements {
    public DOVLanguages is_ready() {
        isItVisible(Header);
        isItInteractable(NewLanguage);
        return this;
    }

    public DOVLanguages new_dov_language(String dov_type, String dov_reason,
                                         String product) {
        clickOn(NewLanguage);
        getNewDoVLanguage().is_ready().save(dov_type, dov_reason, product);
        return this;
    }

    public DOVLanguages verify_dov_language() {
        isItInteractable(SelectAll);
        isItInteractable(DovLanguage);
        return this;
    }

    public void back_to_contract() {
        clickOn(Contract);
    }

    private NewDoVLanguage newDoVLanguage;

    public DOVLanguages(WebDriver driver) {
        super(driver);
        setNewDoVLanguage(new NewDoVLanguage(lDriver));
    }
}
package com.my.salesforce.sandbox.application.pages.organisations;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.organisations.OrganisationsElements;
import com.my.salesforce.sandbox.application.pages.organisations.details.OrganisationDetails;
import com.my.salesforce.sandbox.application.pages.organisations.new_organisation.NewOrganisation;
import lombok.*;
import org.openqa.selenium.*;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class Organisations extends BasePage implements OrganisationsElements {
    public Organisations is_ready() {
        shouldHaveText(Heading.get(0), "Organisations");
        shouldHaveText(Heading.get(1), "Recently Viewed");
        isItInteractable(SearchTheList);
        return this;
    }

    public Organisations new_organisation(String org_name, String abn, String acn, String limit,
                                          String b_street, String b_city, String b_state,
                                          String b_code, String b_country, String p_street,
                                          String p_city, String p_state, String p_code,
                                          String p_country) {
        clickOn(New);
        getNewOrganisationPage().is_ready().save().validate_error()
                .enter_information(org_name, abn, acn, limit)
                .enter_billing_address(b_street, b_city, b_state, b_code, b_country)
                .enter_physical_address(p_street, p_city, p_state, p_code, p_country)
                .save();
        return this;
    }

    public Organisations search_for(String org_name) {
        clear(SearchTheList);
        actions().pause(Duration.ofSeconds(3)).sendKeys($(SearchTheList), org_name)
                .sendKeys(Keys.ENTER).pause(Duration.ofSeconds(3)).build().perform();
        clickOn($(By.xpath("//a[@title='" + org_name + "']")));
        return this;
    }

    private OrganisationDetails organisationDetails;
    private NewOrganisation newOrganisationPage;

    public Organisations(WebDriver driver) {
        super(driver);
        setOrganisationDetails(new OrganisationDetails(lDriver));
        setNewOrganisationPage(new NewOrganisation(lDriver));
    }
}
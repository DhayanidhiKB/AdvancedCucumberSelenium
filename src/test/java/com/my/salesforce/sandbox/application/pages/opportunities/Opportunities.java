package com.my.salesforce.sandbox.application.pages.opportunities;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.OpportunitiesElements;
import com.my.salesforce.sandbox.application.pages.opportunities.details.headers.OpportunityHeader;
import com.my.salesforce.sandbox.application.pages.opportunities.new_opportunity.NewOpportunity;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class Opportunities extends BasePage implements OpportunitiesElements {
    public Opportunities is_ready() {
        shouldHaveText(Heading.get(0), "Opportunities");
        shouldHaveText(Heading.get(1), "Recently Viewed");
        isItInteractable(SearchTheList);
        return this;
    }

    public Opportunities new_opportunity(String sub_type, String stage, String next_step, String opp_name,
                                         String org_name, String first_name, String last_name,
                                         String date, String is_it, String postcode, String monthly_spend,
                                         String opp_value, String type) {
        clickOn(New);
        getNewOpportunityPage().is_it_ready().save().validate_error()
                .enter(sub_type, stage, next_step, opp_name, org_name, first_name, last_name,
                        date, is_it, postcode, monthly_spend, opp_value, type)
                .save();
        return this;
    }

    private NewOpportunity newOpportunityPage;
    private OpportunityHeader opportunityHeader;

    public Opportunities(WebDriver driver) {
        super(driver);
        setNewOpportunityPage(new NewOpportunity(lDriver));
        setOpportunityHeader(new OpportunityHeader(lDriver));
    }
}
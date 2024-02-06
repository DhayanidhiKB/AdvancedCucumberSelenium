package com.my.salesforce.sandbox.application.pages.opportunities.new_opportunity;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.new_opportunity.NewOpportunityPageElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.util.*;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class NewOpportunity extends BasePage implements NewOpportunityPageElements {
    public NewOpportunity is_it_ready() {
        $(Header).shouldBe(visible).shouldHave(appear);
        return this;
    }

    public NewOpportunity enter(String sub_type, String stage, String next_step, String opp_name,
                                String org_name, String first_name, String last_name,
                                String date, String is_it_startrack, String postcode,
                                String monthly_spend, String opp_value, String type) {
        getInformation()
                .is_ready().choose_subType_as(sub_type)
                .choose_stage_as(stage).choose_next_step_as(next_step)
                .enter_name_as(opp_name).search_for_entity_name(org_name)
                .search_for_key_contact(first_name, last_name).close_date(date)
                .getStarTrackDetails()
                .is_ready().fill(is_it_startrack, postcode, monthly_spend);
        getRevenue().is_ready().enter_total_value_as(opp_value);
        getSummary().choose_type_as(type);
        return this;
    }

    public NewOpportunity save() {
        clickOn(Save);
        return this;
    }

    public NewOpportunity validate_error() {
        List<String> mandatory_fields = Arrays.asList("Opportunity SubType", "Opportunity Name", "Stage",
                "Legal Entity Name", "Key Contact", "Close Date",
                "Total Opportunity Value", "Type");
        ErrorLinks.shouldBe(size(8)).shouldHave(size(8));
        MandatoryFields.shouldHave(exactTexts(mandatory_fields));
        clickOn(ErrorIcon);
        return this;
    }

    private Information information;
    private Revenue revenue;
    private Summary summary;

    public NewOpportunity(WebDriver driver) {
        super(driver);
        setInformation(new Information(lDriver));
        setRevenue(new Revenue(lDriver));
        setSummary(new Summary(lDriver));
    }
}
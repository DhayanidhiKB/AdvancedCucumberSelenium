package com.my.salesforce.sandbox.application.pages.opportunities.details;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.details.OpportunityTabSetElements;
import com.my.salesforce.sandbox.application.pages.dsr.SupportRequests;
import com.my.salesforce.sandbox.application.pages.opportunities.creditassessment.CreditAssessmentPage;
import com.my.salesforce.sandbox.application.pages.opportunities.related.Related;
import lombok.*;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class OpportunityTabSet extends BasePage implements OpportunityTabSetElements {
    public OpportunityTabSet is_ready() {
        isItInteractable(CustomerNeeds);
        isItInteractable(ProductsContracts);
        isItInteractable(More);
        return this;
    }

    public OpportunityTabSet products_contracts() {
        clickOn(ProductsContracts);
        return this;
    }

    public OpportunityTabSet credit_assessment() {
        if (locateElements(Link, "Credit Assessment").size() > 0) {
            clickOn(locate(Link, "Credit Assessment"));
        } else {
            mouseOverAndClickOn($(More));
            clickOn($(locate(SubLink, "Credit Assessment")));
        }
        return this;
    }

    public OpportunityTabSet related() {
        if (locateElements(Link, "Related").size() > 0) {
            clickOn(locate(Link, "Related"));
        } else {
            mouseOverAndClickOn($(More));
            clickOn($(locate(SubLink, "Related")));
        }
        return this;
    }

    public OpportunityTabSet support_requests() {
        if (locateElements(Link, "Support Requests").size() > 0) {
            clickOn(locate(Link, "Support Requests"));
        } else {
            mouseOverAndClickOn($(More));
            clickOn($(locate(SubLink, "Support Requests")));
        }
        return this;
    }

    private OpportunityDetails details;
    private ProductsAndContracts productsAndContracts;
    private SupportRequests supportRequestsPage;
    private CreditAssessmentPage creditAssessmentPage;
    private Related related;

    public OpportunityTabSet(WebDriver driver) {
        super(driver);
        setDetails(new OpportunityDetails(lDriver));
        setProductsAndContracts(new ProductsAndContracts(lDriver));
        setCreditAssessmentPage(new CreditAssessmentPage(lDriver));
        setSupportRequestsPage(new SupportRequests(lDriver));
        setRelated(new Related(lDriver));
    }
}
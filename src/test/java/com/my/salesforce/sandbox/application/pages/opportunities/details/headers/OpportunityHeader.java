package com.my.salesforce.sandbox.application.pages.opportunities.details.headers;

import com.codeborne.selenide.Selenide;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.details.sub_header.OpportunityHeaderElements;
import com.my.salesforce.sandbox.application.pages.dsr.NewDealSupportRequest;
import com.my.salesforce.sandbox.application.pages.opportunities.details.CloseOpportunity;
import com.my.salesforce.sandbox.application.pages.shopping_cart.AllProducts;
import lombok.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class OpportunityHeader extends BasePage implements OpportunityHeaderElements {
    public OpportunityHeader is_ready(String opp_name) {
        isItVisible(locate(OpportunityName, opp_name));
        return this;
    }

    public OpportunityHeader page_refresh() {
        Selenide.refresh();
        return this;
    }

    public OpportunityHeader verify_opportunity_actions() {

        isItInteractable(BulkEditProducts);
        return this;
    }

    public OpportunityHeader add_pricing_product() {
        clickOn(AddPricingProducts);
        return this;
    }

    public OpportunityHeader new_deal_support_request() {
        if ($$(NewDealSupportRequestLink).size() > 0) {
            clickOn($(NewDealSupportRequestLink));
        } else {
            clickOn(MoreLinks);
            clickOn(NewDealSupportRequestSubLink);
        }
        return this;
    }

    public OpportunityHeader close_opportunity(String stage_name, String next_steps,
                                               String reason, String comment) {
        if ($$(CloseOpportunityLink).size() > 0) {
            clickOn($(CloseOpportunityLink));
        } else {
            clickOn(MoreLinks);
            clickOn(CloseOpportunitySubLink);
        }
        getCloseOpportunity().confirm_opc().close_opportunity(stage_name, next_steps,
                reason, comment);
        if (stage_name.equals("Closed Won")) {
            getCloseOpportunity().data_integrity_compliance().confirm_dsr();
        }
        getCloseOpportunity().close();
        return this;
    }

    public OpportunityHeader opportunity_stage_is(String stage) {
        isItInteractable(locate(OpportunityStage, stage));
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        return this;
    }

    private CloseOpportunity closeOpportunity;
    private OpportunitySubHeader opportunitySubHeader;
    private NewDealSupportRequest newDealSupportRequestPage;
    private AllProducts allProducts;

    public OpportunityHeader(WebDriver driver) {
        super(driver);
        setCloseOpportunity(new CloseOpportunity(lDriver));
        setOpportunitySubHeader(new OpportunitySubHeader(lDriver));
        setNewDealSupportRequestPage(new NewDealSupportRequest(lDriver));
        setAllProducts(new AllProducts(lDriver));
    }
}
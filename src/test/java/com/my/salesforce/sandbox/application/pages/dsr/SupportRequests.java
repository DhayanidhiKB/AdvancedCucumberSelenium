package com.my.salesforce.sandbox.application.pages.dsr;

import com.codeborne.selenide.CollectionCondition;
import com.google.common.util.concurrent.Uninterruptibles;
import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.dsr.SupportRequestsPageElements;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class SupportRequests extends BasePage implements SupportRequestsPageElements {
    public SupportRequests is_ready() {
        isItVisible(Header);
        isItVisible(Title);
        isItVisible(DsrTable);
        return this;
    }

    public int get_noOf_support_requests() {
        return NoOfDSRs.size();
    }

    public void noOf_support_requests(int count) {
        NoOfDSRs.shouldHave(CollectionCondition.size(count));
    }

    public void open_dsr(int dsrIndex) {
        executeJavaScript("window.scrollBy(0,150)");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        clickOn(NoOfDSRs.get(dsrIndex));
    }

    public SupportRequests(WebDriver driver) {
        super(driver);
    }
}
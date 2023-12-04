package com.my.salesforce.sandbox.application.pages.opportunities.related.csq.new_freight;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.opportunities.related.csq.new_freight.PremiumElements;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class Cartons extends BasePage implements PremiumElements {
    public void cartons_details(String b2b, String b2c, String items, String connotes) {
        actions().pause(Duration.ofSeconds(3)).moveToElement(Cartons).click().build().perform();
        Cartons.scrollIntoView(true);
        setValue(b2b, locate(Input, "B2B%", "1"));
        setValue(b2c, locate(Input, "B2C%", "1"));
        setValue(items, locate(Input, "Cartons Qty Items (Monthly)", "1"));
        setValue(connotes, locate(Input, "Qty Connotes (Monthly)", "1"));
        locate(Input, "B2C%", "1").scrollIntoView(true);
    }

    public Cartons(WebDriver driver) {
        super(driver);
    }
}
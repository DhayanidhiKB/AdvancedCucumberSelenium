package com.my.salesforce.sandbox.application.pages;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.pages.welcome.LoginPage;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class Salesforce extends BasePage {
    public Salesforce visit(String app_url) {
        browser.maximize();
        browser.navigateTo(app_url);
        return this;
    }

    public void shut_down() {
        browser.closeAll();
    }

    private LoginPage loginPage;

    public Salesforce(WebDriver driver) {
        super(driver);
        setLoginPage(new LoginPage(lDriver));
    }
}
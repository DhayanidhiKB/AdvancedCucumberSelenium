package com.my.salesforce.sandbox.application.pages.welcome;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.welcome.LoginPageElements;
import lombok.*;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class LoginPage extends BasePage implements LoginPageElements {
    public LoginPage is_ready() {
        //isItVisible(Logo);
        return this;
    }

    public LoginPage login_as(String username, String password) {
        getUserLogin().enter_username(username).enter_password(password).log_in();
        return this;
    }

    private UserLogin userLogin;
    private Header header;

    public LoginPage(WebDriver driver) {
        super(driver);
        setUserLogin(new UserLogin(lDriver));
        setHeader(new Header(lDriver));
    }
}
package com.my.salesforce.sandbox.application.pages.welcome;

import com.my.salesforce.sandbox.application.BasePage;
import com.my.salesforce.sandbox.application.elements.welcome.LoginPageElements;
import org.openqa.selenium.WebDriver;

public class UserLogin extends BasePage implements LoginPageElements {
    public UserLogin is_ready() {
        isItInteractable(RememberMe);
        isItInteractable(ForgotPassword);
        return this;
    }

    public UserLogin enter_username(String username) {
        setValue(username, Username);
        return this;
    }

    public UserLogin enter_password(String password) {
        setValue(password, Password);
        return this;
    }

    public void log_in() {
        clickOn(LogIn);
    }

    public UserLogin(WebDriver driver) {
        super(driver);
    }
}
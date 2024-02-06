package com.my.salesforce.sandbox.application.elements.welcome;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface LoginPageElements {
    SelenideElement Logo = $(By.id("logo"));
    SelenideElement Username = $(By.id("username"));
    SelenideElement Password = $(By.id("password"));
    SelenideElement LogIn = $(By.id("Login"));
    SelenideElement RememberMe = $(By.id("rememberUn"));
    SelenideElement ForgotPassword = $(By.id("forgot_password_link"));
}
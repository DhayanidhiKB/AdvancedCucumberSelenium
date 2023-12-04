package com.my.salesforce.sandbox.application.elements.proposal;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface EmailPageElements {
    SelenideElement PageDescription = $(By.xpath("//h2[contains(text(),'Send an Email')]"));
    String Options = "(//input[@name='%value%'])[1]";
    SelenideElement To = $(By.xpath("//input[@name='p2']"));
    SelenideElement LookUp = $(By.xpath("//a[@title='To Lookup (New Window)']"));
    SelenideElement Go = $(By.xpath("//input[@name='go']"));
    SelenideElement AdditionalTo = $(By.xpath("//textarea[@name='p24']"));
    SelenideElement CC = $(By.xpath("//textarea[@name='p4']"));
    String name = "//a[text()='%value%']";
    SelenideElement ConfirmationPanel = $(By.xpath("//div[@id='confirmationPanel']"));
    SelenideElement ConfirmationPanelText = $(By.xpath("//div[contains(text(),'Was the Proposal Sent ?')]"));
    String WasProposalSent = "//input[@value='%value%']";
}
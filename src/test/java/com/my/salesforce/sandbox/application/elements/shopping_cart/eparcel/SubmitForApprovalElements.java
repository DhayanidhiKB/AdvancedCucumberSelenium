package com.my.salesforce.sandbox.application.elements.shopping_cart.eparcel;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface SubmitForApprovalElements {
    SelenideElement Header = $(By.xpath("//p[text()='Approval Status: Approval Required']"));
    SelenideElement ReturnToCart = $(By.xpath("//input[@value='Return to Cart']/ancestor::li"));
    SelenideElement SubmitWithAttachments = $(By.xpath("//input[@value='Submit (With Attachments)']/ancestor::li"));
    SelenideElement Submit = $(By.xpath("//input[@value='Submit']/ancestor::li"));
    SelenideElement PageHeader = $(By.xpath("//div[@class='pbHeader']"));
    SelenideElement PageBody = $(By.xpath("//div[@class='pbBody']"));
    SelenideElement CartLink = $(By.xpath("//a[text()='Cart Link']"));
    SelenideElement SubmissionComments = $(By.xpath("//td[contains(text(),'Enter a submission comment.')]/parent::tr/following-sibling::tr//textarea"));
    SelenideElement MessageCell = $(By.xpath("//td[@class='messageCell']//div[text()='Your request(s) have been submitted for approval. You will be notified when approvals are complete.']"));
    SelenideElement Return = $(By.xpath("//input[@value='Return']/ancestor::li"));
}
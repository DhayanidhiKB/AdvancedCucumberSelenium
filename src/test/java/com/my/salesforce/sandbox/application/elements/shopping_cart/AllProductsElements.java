package com.my.salesforce.sandbox.application.elements.shopping_cart;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public interface AllProductsElements {
    SelenideElement iFrame = $(By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]"));
    SelenideElement Like = $(By.xpath("//md-icon[@aria-label='thumb_up']"));
    SelenideElement Cart = $(By.xpath("//md-icon[@aria-label='shopping_cart']"));
    SelenideElement ReviewCart = $(By.xpath("//button[@buttonid='id_task_right_gotopricing']"));
    SelenideElement AbandonCart = $(By.xpath("//button[normalize-space()='Abandon Cart']"));
    SelenideElement SortBy = $(By.xpath("(//a[@aria-label='Select box select'])[2]"));
    String ProductLink = "//a[text()='%value%']";
    String CheckBox = "//a[text()='%value%']/ancestor::div[@class='listing-info']/preceding-sibling::span//label";
    SelenideElement FindProducts = $(By.xpath("//input[@placeholder='Find Products']"));
    SelenideElement AddToCart = $(By.xpath("(//span[contains(text(),'Add to Cart')]/parent::button)[1]"));
    String ConfigureProduct = "(//span[text()='%value%']/parent::h3/descendant::i)[1]";
}
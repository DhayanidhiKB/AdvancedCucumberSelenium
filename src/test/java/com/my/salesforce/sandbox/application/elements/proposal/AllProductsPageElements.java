package com.my.salesforce.sandbox.application.elements.proposal;

import org.openqa.selenium.By;

public interface AllProductsPageElements {
    By iFrame = By.xpath("//div[@class='oneAlohaPage']//iframe[contains(@name,'vfFrameId')]");
    By Like = By.xpath("//md-icon[@aria-label='thumb_up']");
    By Cart = By.xpath("//md-icon[@aria-label='shopping_cart']");
    By Exit = By.xpath("//md-icon[text()='exit_to_app']");
    By ReviewCart = By.xpath("//button[@buttonid='id_task_right_gotopricing']");
    By AbandonCart = By.xpath("//button[@buttonid='id_task_right_closecart']");
    By SortBy = By.xpath("(//a[@aria-label='Select box select'])[2]");
    String ProductLink = "//a[text()='%value%']";
    String CheckBox = "//a[text()='%value%']/ancestor::div[@class='listing-info']/preceding-sibling::span//label";
    By FindProducts = By.xpath("//input[@placeholder='Find Products']");
    By AddToCart = By.xpath("(//span[contains(text(),'Add to Cart')]/parent::button)[1]");
    String ConfigureProduct = "(//span[text()='%value%']/parent::h3/descendant::i)[1]";
}
package com.my.salesforce.sandbox.application.elements.opportunities.related.csq.new_freight;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public interface NewFreightOfferingElements {
    SelenideElement Header = $(By.xpath("//h2[text()='New Freight Offering']"));
    String RecordType = "//span[text()='%value%']/parent::div/preceding-sibling::div//span";
    String Button = "//div[@class='forceChangeRecordTypeFooter']/button/span[text()='%value%']";
    String DualListBox = "//div[text()='%value1%']/following-sibling::div/descendant::ul/li//span[@title='%value2%']";
    String Arrow = "(//div[text()='%value1%']/following-sibling::div/descendant::button[@title='Move selection to %value2%'])[1]";
    String Input = "(//label[text()='%value1%']/following-sibling::div/input)[%value2%]";
    String ListBox = "//label[text()='%value%']/following-sibling::div//button";
    String Option = "//span[text()='%value%']";
    SelenideElement Save = $(By.xpath("//button[text()='Save']"));
}
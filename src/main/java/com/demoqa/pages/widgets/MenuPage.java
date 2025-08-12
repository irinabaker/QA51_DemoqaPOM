package com.demoqa.pages.widgets;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPage extends BasePage {

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[.='Main Item 2']")
    WebElement mainItem2;
    @FindBy(xpath = "//a[.='SUB SUB LIST »']")
    WebElement subSubList;
    @FindBy(xpath = "//a[.='Sub Sub Item 1']")
    WebElement subItem1;

    public MenuPage hoverMouseOnSubMenu() {
        pause(1000);
        scrollWithJs(0,300);
        actions.moveToElement(mainItem2).perform();
        actions.moveToElement(subSubList).perform();
        return this;
    }

    public MenuPage verifySubMenu() {
        Assertions.assertTrue(isElementVisible(subItem1));
        return this;
    }

}

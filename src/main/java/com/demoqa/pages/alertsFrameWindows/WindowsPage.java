package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class WindowsPage extends BasePage {

    public WindowsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "tabButton")
    WebElement tabButton;

    public WindowsPage switchToNewTab(int index) {
        clickWithJs(tabButton,0,300);

        switchToNewWindow(index);
        return this;
    }

    @FindBy(id = "sampleHeading")
    WebElement sampleHeading;

    public WindowsPage verifyNewTabTitle(String title) {
        Assertions.assertTrue(shouldHaveText(sampleHeading,title,5));
        return this;
    }
}

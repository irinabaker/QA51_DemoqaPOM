package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class IframePage extends BasePage {

    public IframePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "iframe")
    List<WebElement> iframes;

    public IframePage returnListOfIframes() {
        //using tag
        System.out.println("The total number of iframes are " + iframes.size());
        //by executing js
        Integer numberOfIframes = Integer.parseInt(js.executeScript("return window.length").toString());
        System.out.println("The total number of iframes are " + numberOfIframes);
        return this;
    }

    public IframePage switchToIframeByIndex(int index) {
        driver.switchTo().frame(index);
        return this;
    }

    @FindBy(id = "sampleHeading")
    WebElement sampleHeading;

    public IframePage verifyIframeByTitle(String title) {
        Assertions.assertTrue(isContainsText(title,sampleHeading));
        return this;
    }

    @FindBy(id = "frame1")
    WebElement frame1;

    public IframePage switchToIframeById() {
        driver.switchTo().frame(frame1);
        return this;
    }

    public IframePage switchToMainPage() {
        driver.switchTo().defaultContent();
        return this;
    }

    @FindBy(css = "h1")
    WebElement title;

    public IframePage verifyMainPageByTitle(String text) {
        Assertions.assertTrue(isContainsText(text,title));
        return this;
    }

    @FindBy(css = "body")
    WebElement body;

    public IframePage handleNestedIframes() {
        //switch to parent by ID
        driver.switchTo().frame(frame1);
        //assert by text
        softly.assertThat(isContainsText("Parent frame",body));
        //assert by number
        softly.assertThat(iframes.size()).isEqualTo(1);
        //switch to child by index
        driver.switchTo().frame(0);
        //assert by text
       softly.assertThat(isContainsText("Child Iframe",body));
        //switch to parent
        driver.switchTo().parentFrame();
        //assert by text
        softly.assertThat(isContainsText("Parent frame",body));
        softly.assertAll();
        return this;
    }
}

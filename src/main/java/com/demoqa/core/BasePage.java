package com.demoqa.core;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

   protected WebDriver driver;
   public static JavascriptExecutor js;
   public static Actions actions;
   public static SoftAssertions softly;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        softly = new SoftAssertions();
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollWithJs(int x, int y) {
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
    }

    public void click(WebElement element) {
        element.click();
    }

    public void clickWithJs(WebElement element, int x, int y) {
        scrollWithJs(x,y);
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void typeWithJs(WebElement element, String text, int x, int y) {
        scrollWithJs(x,y);
        type(element,text);
    }

    public boolean isContainsText(String userName, WebElement element) {
        return element.getText().contains(userName);
    }

    public boolean shouldHaveText(WebElement element, String text, int time) {
        return wait(time)
                .until(ExpectedConditions.textToBePresentInElement(element,text));
    }

    public boolean isAlertPresent(int time) {
        Alert alert = wait(time)
                .until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        }
        alert.accept();
        return true;
    }

    public WebDriverWait wait(int time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time));
    }

    public void switchToNewWindow(int index) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index));
    }

    public boolean isContainsCssValue(String text, WebElement element, String cssValue) {
        return element.getCssValue(cssValue).contains(text);
    }

    public boolean isElementVisible(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException ex) {
            ex.getMessage();
            return false;
        }
    }

    public void waitAndScroll() {
        pause(1000);
        scrollWithJs(0,300);
    }

    public void verifyLinks(String url) {

        try {
            URL linkUrl = new URL(url);
            //create URL connection and get response code
            HttpURLConnection connection = (HttpURLConnection) linkUrl.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();
            if (connection.getResponseCode()>=400) {
              //  System.out.println(url + " - " + connection.getResponseMessage() + " is a broken link");
                softly.fail(url + " - " + connection.getResponseMessage() + " is a broken link");
            } else {
              //  System.out.println(url + " - " + connection.getResponseMessage());
                softly.assertThat(connection.getResponseCode()).isLessThan(400);
            }
        } catch (Exception e) {
          //  System.out.println(url + " - " + e.getMessage() + " ERROR occurred");
            softly.fail(url + " - " + e.getMessage() + " ERROR occurred");
        }
          softly.assertAll();
    }

}

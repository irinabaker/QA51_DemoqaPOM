package com.demoqa.utils;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

public class MyListener implements WebDriverListener {

    Logger logger = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        logger.error("Something went wrong!!!");
        logger.info("*********************************************");
        logger.error("Method {} failed",method.getName());
        logger.info("===============================");
        logger.error("Target is {}",target);
        logger.info("===============================");
        logger.info("Exception is {}",e.getMessage());
        logger.info("==============================");

        int i = new Random().nextInt(1000) + 1000;
        String link = "screenshots/screen_" + i + ".png";
        logger.info("Screen with error --> {}",link);

        WebDriver driver = (ChromeDriver) target;
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File tmp = screenshot.getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(tmp,new File(link));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void beforeGet(WebDriver driver, String url) {
        logger.info("Open url - {}",url);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
      logger.info("Element {} found",result.toString());
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        logger.info("{} number of elements found", result.size());
    }

    @Override
    public void afterClick(WebElement element) {
        logger.info("We clicked on element {}",element.toString());
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        logger.info("We sendKeys to element {}",element.toString());
    }
}

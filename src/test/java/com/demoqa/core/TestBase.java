package com.demoqa.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {

    protected ApplicationManager app = new ApplicationManager(System.getProperty("browser","chrome"));

    protected WebDriver driver;
    public static Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeEach
    public void init(TestInfo info) {
        driver = app.startTest();
        logger.info("Start test {}",info.getDisplayName());
    }

    @AfterEach
    public void tearDown() {
        app.stopTest();
        logger.info("Stop test");
        logger.info("********************************************");
    }

}

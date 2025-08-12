package com.demoqa.utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyWatcher implements TestWatcher {

    Logger logger = LoggerFactory.getLogger(MyWatcher.class);

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.error("The method !!{}!! is FAILED",context.getDisplayName());
        logger.error("{}", cause.getMessage());
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.info("The method {} is PASSED", context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
       logger.error("The method {} is ABORTED",context.getDisplayName());
       logger.error("{}",cause.getMessage());
    }
}

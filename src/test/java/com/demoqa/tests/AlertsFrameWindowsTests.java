package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.pages.alertsFrameWindows.IframePage;
import com.demoqa.pages.alertsFrameWindows.WindowsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AlertsFrameWindowsTests extends TestBase {

    AlertsPage alerts;
    SidePanel sidePanel;
    IframePage iframe;

    @BeforeEach
    public void precondition() {
        alerts = new AlertsPage(driver);
        sidePanel = new SidePanel(driver);
        iframe = new IframePage(driver);
        new HomePage(driver).selectAlertsFrameWindows();
    }

    @Test
    public void waitAlertTest() {
        sidePanel.selectAlerts();
        alerts.verifyAlertWithTimer();
    }

    @Test
    @DisplayName("Verify to - 'Cancel' is displayed")
    @Tag("smoky")
    public void alertWithSelectResultTest() {
        sidePanel.selectAlerts();
        alerts.selectResult("Cancel")
                .verifyResult("Cancel");
    }

    @Test
    public void sendMessageToAlertTest() {
        sidePanel.selectAlerts();
        alerts.sendMessageToAlert("Hello World!")
                .verifyMessage("Hello World!");
    }

    @Test
    @Tag("smoky")
    public void switchToNewTabTest() {
        sidePanel.selectBrowserWindows();
        new WindowsPage(driver).switchToNewTab(1)
                .verifyNewTabTitle("This is a sample page");
    }

    @Test
    public void switchToIframeByIndexTest() {
        sidePanel.selectFrame();
        iframe.returnListOfIframes()
                .switchToIframeByIndex(1)
                .verifyIframeByTitle("This is a sample page");
    }

    @Test
    @Tag("smoky")
    public void switchToIframeByIdTest() {
        sidePanel.selectFrame();
        iframe.switchToIframeById()
                .verifyIframeByTitle("sample page")
                .switchToMainPage()
                .verifyMainPageByTitle("Frames");
    }

    @Test
    public void nestedIframesTest() {
        sidePanel.selectNestedFrames();
        iframe.handleNestedIframes();
    }
}

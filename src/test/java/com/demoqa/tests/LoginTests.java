package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.data.UserData;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.bookStore.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    @BeforeEach
    public void precondition() {
        new HomePage(driver).selectBookStore();
        new SidePanel(driver).selectLogin();
    }

    @Test
    @DisplayName("Check to - 'User name neuer' is displayed")
    public void loginPositiveTest() {
        logger.info("I log in with userName - {} and password - {}", UserData.user, UserData.password);
        new LoginPage(driver).enterUserData(UserData.user, UserData.password)
                .clickOnLoginButton()
                .verifyUserName(UserData.user);
    }
}

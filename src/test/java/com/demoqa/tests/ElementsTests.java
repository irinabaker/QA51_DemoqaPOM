package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.elements.ButtonsPage;
import com.demoqa.pages.elements.LinksPage;
import com.demoqa.pages.elements.TextBoxPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.demoqa.core.BasePage.softly;

public class ElementsTests extends TestBase {

    SidePanel sidePanel;
    ButtonsPage buttons;
    LinksPage links;

    @BeforeEach
    public void precondition() {
        sidePanel = new SidePanel(driver);
        buttons = new ButtonsPage(driver);
        links = new LinksPage(driver);
        new HomePage(driver).selectElements();
    }

    @Test
    public void doubleClickTest() {
        sidePanel.selectButtons();
        buttons.doubleClick()
                .verifyDoubleClick("double click");
    }

    @Test
    public void rightClickTest() {
        sidePanel.selectButtons();
        buttons.rightClick()
                .verifyRightClick("right click");
    }

    @Test
    public void jsExecutorTest() {
        sidePanel.selectTextBox();
        new TextBoxPage(driver).enterPersonalData("Jamal Musiala", "jamal@gm.com")
                .clickOnSubmitButton()
                .getInnerText()
                .verifyURL()
                .navigateToNewPage()
                .verifyNewPageTitle()
                ;
    }

    @Test
    public void getAllLinksTest() {
        sidePanel.selectLinks();
        links.getAllUrl();
    }

    @Test
    public void checkBrokenLinksTest() {
        sidePanel.selectBrokenLinks();
        links.checkBrokenLinks();
    }

    @Test
    public void checkBrokenImagesTest() {
        sidePanel.selectBrokenLinks();
        links.checkBrokenImages();
    }

}

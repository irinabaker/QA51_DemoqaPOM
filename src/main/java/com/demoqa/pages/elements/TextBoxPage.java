package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;

public class TextBoxPage extends BasePage {

    public TextBoxPage(WebDriver driver) {
        super(driver);
    }

    public TextBoxPage enterPersonalData(String name, String email) {
        js.executeScript("document.getElementById('userName').value='" + name + "';");
        js.executeScript("document.getElementById('userName').style.border='5px solid red';");
        js.executeScript("document.getElementById('userEmail').value='" + email + "';");
        js.executeScript("document.getElementById('userEmail').style.border='5px solid green';");
        return this;
    }

    public TextBoxPage clickOnSubmitButton() {
        js.executeScript("document.querySelector('.btn-primary').click();");
        js.executeScript("document.querySelector('.btn-primary').style.backgroundColor='red';");
        return this;
    }

    public TextBoxPage getInnerText() {
        String innerText = js.executeScript("return document.documentElement.innerText;").toString();
        System.out.println(innerText);
        System.out.println("*******************************************");
        return this;
    }

    public TextBoxPage verifyURL() {
        String url = js.executeScript("return document.URL;").toString();
        System.out.println("URL = " + url);
        System.out.println("*************************************");
        return this;
    }

    public TextBoxPage navigateToNewPage() {
        js.executeScript("window.location='https://ticket-service-69443.firebaseapp.com';");
        return this;
    }

    public TextBoxPage verifyNewPageTitle() {
        String pageTitle = js.executeScript("return document.title;").toString();
        System.out.println(pageTitle);
        return this;
    }
}

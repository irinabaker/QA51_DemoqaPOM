package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.data.UserData;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.bookStore.BookStorePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SearchBookTests extends TestBase {

    @BeforeEach
    public void precondition() {
        new HomePage(driver).selectBookStore();
    }

    @Test
    public void searchBookTest() {
        new BookStorePage(driver).enterBookName(UserData.bookName)
                .verifyBookName(UserData.bookName);
    }
}

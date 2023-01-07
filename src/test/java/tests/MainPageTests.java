package tests;

import org.junit.jupiter.api.Test;
import pages.MainPage;

public class MainPageTests {

    MainPage mainPage = new MainPage();

    @Test
    void searchVacancy() {
        mainPage.searchVacancy();
    }
}
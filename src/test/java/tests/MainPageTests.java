package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.MainPage;

public class MainPageTests {

    MainPage mainPage = new MainPage();

    @BeforeAll
    static void setUp() {
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("enableVNC", true);
//        capabilities.setCapability("enableVideo", true);

//        String baseUrl = "https://demoqa.com";
        String browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browser_version", "100.0");
        String browserSize = System.getProperty("browser_size", "1920x1080");
        String remote = System.getProperty("remote");

//        Configuration.baseUrl = baseUrl;
        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.browserSize = browserSize;

        if (remote != null) {
            Configuration.remote = remote;
        }
    }

    @Test
    void searchVacancy() {
        mainPage.searchVacancy();
    }
}
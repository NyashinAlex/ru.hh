package tests;

import com.codeborne.selenide.Selenide;
import config.WebDriverProvider;
import helper.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void setUp() {
        WebDriverProvider.configure();
    }

    @AfterEach
    void close() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
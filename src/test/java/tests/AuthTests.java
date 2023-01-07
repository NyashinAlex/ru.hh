package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AuthPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class AuthTests extends BaseTest{

    AuthPage authPage = new AuthPage();

    @Test
    @DisplayName("Успешная авторизация")
    void successAuth() {
        authPage
                .clickEnterWithPassword()
                .writeLoginAndPass()
                .clickEnter();

        $(".supernova-icon.supernova-icon_profile").click();
        $(".supernova-dropdown .bloko-text.bloko-text_strong").shouldBe(text("Александр Няшин"));
    }
}

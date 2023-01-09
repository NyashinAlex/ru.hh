package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AuthPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class AuthTests extends BaseTest {

    Faker faker = new Faker();
    AuthPage authPage = new AuthPage();

    String phone = "89056420821", password = "Nn1031941", nameUser = "Александр Няшин", passwordBad, phoneBad;

    @BeforeEach
    void generationData() {
        passwordBad = String.valueOf(faker.number().numberBetween(80000,899999));
        phoneBad = String.valueOf(faker.number().numberBetween(80000,899999));
    }

    @Test
    @DisplayName("Успешная авторизация")
    void successAuth() {
        authPage
                .clickEnterWithPassword()
                .writeLoginAndPass(phone, password)
                .clickEnter();

        $(".supernova-icon.supernova-icon_profile").click();
        $(".supernova-dropdown .bloko-text.bloko-text_strong").shouldBe(text(nameUser));
    }

    @Test
    @DisplayName("Неуспешная авторизация - неверный пароль")
    void unsuccessfulAuthWrongPassword() {
        authPage
                .clickEnterWithPassword()
                .writeLoginAndPass(phone, passwordBad)
                .clickEnter();

        $(".bloko-form-error.bloko-form-error_entering").shouldBe(text("Неправильные данные для входа. Пожалуйста, попробуйте снова."));
    }

    @Test
    @DisplayName("Неуспешная авторизация - телефон не зарегистрирован в системе")
    void unsuccessfulAuthWrongPhone() {
        authPage
                .clickEnterWithPassword()
                .writeLoginAndPass(phoneBad, password)
                .clickEnter();

        $(".bloko-form-error.bloko-form-error_entering").shouldBe(text("Неправильные данные для входа. Пожалуйста, попробуйте снова."));
    }

    @Test
    @DisplayName("Неуспешная авторизация - поле Телефон не заполнено")
    void unsuccessfulAuthNullPhone() {
        authPage
                .clickEnterWithPassword()
                .writeLoginAndPass("", password)
                .clickEnter();

        $(".bloko-form-error.bloko-form-error_entering").shouldBe(text("Обязательное поле"));
    }

    @Test
    @DisplayName("Неуспешная авторизация - поле Пароль не заполнено")
    void unsuccessfulAuthNullPassword() {
        authPage
                .clickEnterWithPassword()
                .writeLoginAndPass(phone, "")
                .clickEnter();

        $(".bloko-form-error.bloko-form-error_entering").shouldBe(text("Обязательное поле"));
    }
}
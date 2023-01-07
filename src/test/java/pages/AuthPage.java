package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthPage {

    private final SelenideElement
            enterWithPassword = $("[data-qa= expand-login-by-password]"),
            phoneField = $(".bloko-input-text-wrapper [name = username]"),
            passwordField = $(".bloko-input-text-wrapper [type= password]"),
            enterButton = $(".account-login-tile", 0).$(".bloko-button.bloko-button_kind-primary");
//            profileIcon = $(".supernova-icon-link-switch", 6);//.supernova-dropdown .bloko-text.bloko-text_strong

    @Step("Перейти на форму с авторизацией с паролем")
    public AuthPage clickEnterWithPassword() {
        open("/account/login");
        enterWithPassword.click();
        return this;
    }

    @Step("Ввести логин и пароль")
    public AuthPage writeLoginAndPass() {
        phoneField.setValue("89056420821");
        passwordField.setValue("Nn1031941");
        return this;
    }

    @Step("Завершить авторизацию")
    public AuthPage clickEnter() {
        enterButton.click();
        return this;
    }
}
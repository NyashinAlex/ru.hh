package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement searchField = $(".supernova-dashboard-search #a11y-search-input"), titleVacancy = $(".serp-item__title");

    @Step(value = "Поиск вакансии по тексту {value}")
    public MainPage searchVacancy(String value) {
        open("");
        searchField.setValue(value).pressEnter();
        return this;
    }

    @Step(value = "Открытие {num + 1} вакансии")
    public MainPage openVacancy(int num) {
        $(".serp-item__title", num).click();
        return this;
    }
}
package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    private final SelenideElement searchField = $(".supernova-dashboard-search #a11y-search-input");

    @Step(value = "Поиск вакансии")
    public MainPage searchVacancy() {
        open("https://hh.ru/");
        searchField.setValue("Стас").pressEnter();
        return this;
    }
}

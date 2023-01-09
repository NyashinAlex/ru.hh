package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import java.util.Locale;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class MainTests extends BaseTest {

//    Faker faker = new Faker(new Locale("RU"));
    MainPage mainPage = new MainPage();

    String valueVacancy, incorrectValueVacancy;

    @BeforeEach
    void generationData() {
//        valueVacancy = faker.job().position();
        valueVacancy = "QA";
        incorrectValueVacancy = "такой вакансии нет и не будет никогда";
    }

    @Test
    @DisplayName("Успешный поиск по наименованию вакансии")
    void searchVacancy() {
        mainPage.searchVacancy(valueVacancy);

        $(".bloko-column.bloko-column_xs-0.bloko-column_s-8.bloko-column_m-12.bloko-column_l-16 .bloko-header-section-3")
                .shouldBe(text(valueVacancy));
    }

    @Test
    @DisplayName("Проверка тела текста перовой вакансии на соответствие")
    void checkVacancy() {
        mainPage
                .searchVacancy(valueVacancy)
                .openVacancy(0);
        switchTo().window(1);

        $(".vacancy-title").shouldBe(text(valueVacancy));
    }

    @Test
    @DisplayName("Неуспешный поиск по наименованию вакансии - некорректный текст поиска")
    void unsuccessfulSearchVacancyNotCorrectTextValue() {
        mainPage.searchVacancy(incorrectValueVacancy);

        $(".bloko-column.bloko-column_xs-0.bloko-column_s-8.bloko-column_m-12.bloko-column_l-16 .bloko-header-section-3")
                .shouldBe(text("По запросу «" + incorrectValueVacancy + "» ничего не найдено"));
    }

    @Test
    @DisplayName("Успешный поиск по наименованию вакансии - нет текста поиска")
    void successfulSearchVacancyNotTextValue() {
        mainPage.searchVacancy("");

        $$(".serp-item__title").shouldBe(size(50));
        webdriver().shouldHave(urlContaining("https://hh.ru/search/vacancy?text=&"));
    }
}
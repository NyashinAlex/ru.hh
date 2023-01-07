package tests;

import com.codeborne.selenide.ElementsCollection;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class MainTests extends BaseTest {

    Faker faker = new Faker();
    MainPage mainPage = new MainPage();

    String valueVacancy, incorrectValueVacancy;

    @BeforeEach
    void generationData() {
        valueVacancy = faker.job().position();
        incorrectValueVacancy = "такой вакансии нет и не будет никогда";

    }

    @Test
    @DisplayName("Успешный поиск по наименованию вакансии")
    void searchVacancy() {
        mainPage.searchVacancy(valueVacancy);

        $(".bloko-column.bloko-column_xs-0.bloko-column_s-8.bloko-column_m-12.bloko-column_l-16 .bloko-header-section-3").shouldBe(text(valueVacancy));
        ElementsCollection title = $$(".serp-item__title");
        for (int i = 0; i < 50; i++) {
            title.get(i).shouldBe(text(valueVacancy));
        }
    }

    @Test
    @DisplayName("Неуспешный поиск по наименованию вакансии - некорректный текст поиска")
    void unsuccessfulSearchVacancyNotCorrectTextValue() {
        mainPage.searchVacancy(incorrectValueVacancy);

        $(".bloko-column.bloko-column_xs-0.bloko-column_s-8.bloko-column_m-12.bloko-column_l-16 .bloko-header-section-3").shouldBe(text("По запросу «" + incorrectValueVacancy + "» ничего не найдено"));
    }

    @Test
    @DisplayName("Успешный поиск по наименованию вакансии - нет текста поиска")
    void successfulSearchVacancyNotTextValue() {
        mainPage.searchVacancy("");

        $$(".serp-item__title").shouldBe(sizeGreaterThan(50));
        webdriver().shouldHave(url("https://hh.ru/search/vacancy?text=&from=suggest_post&salary=&area=1&ored_clusters=true&enable_snippets=true"));
    }
}
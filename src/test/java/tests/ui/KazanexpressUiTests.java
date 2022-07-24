package tests.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class KazanexpressUiTests {

    @Tag("ui")
    @Test
    @DisplayName("Проверка отображения заголовка страницы")
    void titleTest() {
        step("Открыть главную страницу 'https://kazanexpress.ru/'", () ->
                open("https://kazanexpress.ru/"));

        step("Проверка соответствия заголовка 'KazanExpress - интернет-магазин с бесплатной доставкой за 1 день'", () -> {
            String expectedTitle = "KazanExpress - интернет-магазин с бесплатной доставкой за 1 день";
            String actualTitle = title();
            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Tag("ui")
    @ValueSource(strings = {"Одежда", "Электроника"})
    @ParameterizedTest(name = "При передаче искомого товара в стоку поиска по запросу {0} в результатах отображается товар {0}")
    void searchCategoryTest(String testData) {
        open("https://kazanexpress.ru/");
        $("[data-test-id=input__search]").click();
        $("[data-test-id=input__search]").setValue(testData);
        $("[data-test-id=button__search]").pressEnter();
        $$("[id=caregory-content-wrapper]").find(text(testData)).shouldBe(visible);
    }
//тут не работает проверка
    @Tag("ui")
    @CsvSource(value = {
            "Электроника, Смартфоны и телефоны",
            "Бытовая техника, Крупная бытовая техника"
    })
    @ParameterizedTest(name = "При клике по полю {0} в результатах отображается текст {1}")
    void chooseCityTest(String data, String result) {
        open("https://kazanexpress.ru/");
        $(".bottom-header-wrapper").$(byText(data)).click();
        $$("[id=caregory-content-wrapper]").find(text(result)).shouldBe(visible);
    }

    //TestDataConfig config = ConfigFactory.create(TestDataConfig.class);
    String userName = "Виктория";

    @Tag("ui")
    @Test
    @DisplayName("Проверка авторизации пользователя")
    void authTest() {
        step("Открыть главную страницу 'https://kazanexpress.ru/'", () ->
                open("https://kazanexpress.ru/"));

        step("Нажать кнопку 'Войти'", () ->
                $("[data-test-id='button__auth']").click());

        step("Заполнить форму авторизации", () -> {
//            $("[data-test-id='input__login']").val(config.userLogin());   потом с конфигом будет так
//            $("[data-test-id='input__password']").val(config.userPassword());
            $("[data-test-id='input__login']").val("89312398004");
            $("[data-test-id='input__password']").val("UZTgf2mW");
        });

        step("Нажать на кнопку 'Войти'", () ->
                $("[data-test-id=button__sign-in]").submit());

        step("Проверка, успешной авторизации по имени юзера", () -> {
           // $("[data-test-id=block__cart-items]").shouldHave(text(userName));
            $(byText(userName)).shouldBe(visible);
        });
    }

    @Tag("ui")
    @Test
    @DisplayName("Проверка добавления товара 'Стиральный порошок Tide' в корзину")
    void addProductTest() {
        open("https://kazanexpress.ru/");
        $("[data-test-id=input__search]").click();
        $("[data-test-id=input__search]").setValue("стиральный порошок tide 3 кг");
        $("[data-test-id=button__search]").pressEnter();
        $("[data-test-id=button__add-to-cart]").pressEnter();
    }

    @Tag("ui")
    @Test
    @DisplayName("Проверка возможности увеличения колличества товаров в корзине")
    void addAmountProductTest() {
        open("https://kazanexpress.ru/");
        $("[data-test-id=input__search]").click();
        $("[data-test-id=input__search]").setValue("стиральный порошок tide 3 кг");
        $("[data-test-id=button__search]").pressEnter();
        $("[title='Добавить Стиральный порошок Tide, 3 кг в корзину']").pressEnter();
        $("[data-test-id=button__cart]").click();
        $("[data-test-id=button__increase-quantity]").click();
        $("[data-test-id=input__item-amount]").shouldHave(value("2"));
    }


    @Tag("ui")
    @Test
    @DisplayName("Проверка отсутсвия товара 'книга убить пересмешника' в каталоге товаров")
    void notFoundTest() {
        open("https://kazanexpress.ru/");
        $("[data-test-id=input__search]").click();
        $("[data-test-id=input__search]").setValue("книга убить пересмешника");
        $("[data-test-id=button__search]").pressEnter();
        $(byText("Возможно, в названии товара ошибка или у нас пока нет такого товара")).shouldBe(visible);
    }
}

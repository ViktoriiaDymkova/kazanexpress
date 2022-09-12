package tests.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MainPage {

    //locators
    private final SelenideElement
            searchBox = $("[data-test-id=input__search]"),
            searchButton = $("[data-test-id=button__search]"):

    ElementsCollection
    productList = $$("[id=caregory-content-wrapper]");


    //actions
    void searchCategoryTest(String testData) {
        @Step("Открыть главную страницу 'https://kazanexpress.ru/'")
                open("https://kazanexpress.ru/");
        step("Ввести в поле поиска искомый товар", () -> {
            searchBox.click();
            searchBox.setValue(testData);
            searchButton.pressEnter();
        });
        step("При передаче искомого товара в стоку поиска по запросу {0} в результатах отображается товар {0}", () -> {
            productList.find(text(testData)).shouldBe(visible);
        });
    }

    @Tag("ui")
    @CsvSource(value = {
            "Электроника, Смартфоны и телефоны",
            "Бытовая техника, Крупная бытовая техника"
    })
    @ParameterizedTest(name = "При клике по полю {0} в результатах отображается текст {1}")
    void chooseCityTest(String data, String result) {
        step("Открыть главную страницу 'https://kazanexpress.ru/'", () -> {
            open("https://kazanexpress.ru/");
        });
        step("Выбрать на странице искомый раздел", () -> {
            $(".bottom-header-wrapper").$(byText(data)).click();
        });
        step("Убедиться, что после перехода в искомый раздел, отображаются ожидаемые категории товаров", () -> {
            $$("[id=caregory-content-wrapper]").find(text(result)).shouldBe(visible);
        });
    }

    //TestDataConfig config = ConfigFactory.create(TestDataConfig.class);
    String userName = "Виктория";



    @Tag("ui")
    @Test
    @DisplayName("Проверка добавления товара 'Стиральный порошок Tide' в корзину")
    void addProductTest() {
        step("Открыть главную страницу 'https://kazanexpress.ru/'", () ->
                open("https://kazanexpress.ru/"));
        step("Ввести в поле поиска искомое значение и нажать кнопку 'Добавить в корзину'", () -> {
            $("[data-test-id=input__search]").click();
            $("[data-test-id=input__search]").setValue("стиральный порошок tide 3 кг");
            $("[data-test-id=button__search]").pressEnter();
            $("[data-test-id=button__add-to-cart]").pressEnter();
        });
    }


    @Tag("ui")
    @Test
    @DisplayName("Проверка отсутсвия товара 'книга убить пересмешника' в каталоге товаров")
    void notFoundTest() {
        step("Открыть главную страницу 'https://kazanexpress.ru/'", () ->
                open("https://kazanexpress.ru/"));
        step("Ввести в поле поиска искомый товар 'книга убить пересмешника'", () -> {
            $("[data-test-id=input__search]").click();
            $("[data-test-id=input__search]").setValue("книга убить пересмешника");
            $("[data-test-id=button__search]").pressEnter();
        });
        step("Искомый товар не найден, выводится уведомление с текстом 'Мы не нашли то, что вы искали'", () -> {
            $(byText("Возможно, в названии товара ошибка или у нас пока нет такого товара")).shouldBe(visible);
        });
    }
}
}

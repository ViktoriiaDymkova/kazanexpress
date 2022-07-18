package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static com.codeborne.selenide.Selenide.$;

public class KazanexpressUiTests {

    @Tag("ui")
    @Test
    @DisplayName("Проверка отображения заголовка страницы")
    void titleTest() {
        //что там в заголовке
    }

    @Tag("ui")
    @ValueSource(strings = {"Одежда", "Электроника"})
    @ParameterizedTest(name = "При передаче искомого товара в стоку поиска по запросу {0} в результатах отображается товар {0}")
    void searchCategoryTest(String testData) {
        Selenide.open("https://kazanexpress.ru/");
        $("#input__search").setValue(testData);
        $("#button__search").pressEnter();
        //$().find(text(testData)).shouldBe(visible); /// найти локатор чтобы находилось оба поисковых значения
    }

    @Tag("ui")
    @CsvSource(value = {
            "Пункты выдачи, Бесплатная доставка за сутки в пункты выдачи в 119 городах России",
            "Город, Выберите город доставки"
    })
    @ParameterizedTest(name = "При клике по полю {0} в результатах отображается текст {1}")
    void chooseCityTest(String data, String result) {


    }

    @Tag("ui")
    @Test
    @DisplayName("Проверка авторизации пользователя")
    void authTest() {
    }

    @Tag("ui")
    @Test
    @DisplayName("Проверка добавления товара 'Стиральный порошок Tide' в корзину")
    void addProductTest() {
    }

    @Tag("ui")
    @Test
    @DisplayName("Проверка возможности увеличения колличества товаров в корзине") // как это сделать через клики?
    void addAmountProductTest() {
    }

    @Tag("ui")
    @Test
    @DisplayName("Проверка ближайшей даты доставки") /// проверяем что там не пусто !!!
    void deliveryDateTest() {
    }

    @Tag("ui")
    @Test
    @DisplayName("Проверка отсутсвия товара 'книга убить пересмешника' в каталоге товаров") /// проверяем что есть уведомление
   // Возможно, в названии товара ошибка или у нас пока нет такого товара//
    void notFoundTest() {
    }

}

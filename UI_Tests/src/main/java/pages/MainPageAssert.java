package pages;

import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;

public class MainPageAssert extends AbstractAssert <MainPageAssert,MainPage> {


    public MainPageAssert(MainPage actual) {
        super(actual, MainPageAssert.class);
    }

    @Step("Проверка видимости кнопки корзина")
    public MainPageAssert cartButtonIsVisible() {
        actual.cartBtn.should(visible);
        return this;
    }

    @Step("Проверка видимости кнопки Администрирование")
    public MainPageAssert adminButtonIsVisible() {
        actual.adminBtn.should(visible);
        return this;
    }

    @Step("Проверка видимости заголовка главной страницы")
    public MainPageAssert mainTitleIsVisible() {
        actual.mainTitle.should(visible);
        return this;
    }

    @Step("Проверка количество товаров >= '{size}'")
    public MainPageAssert productListIsGreaterThen(int size) {
        actual.productCardList.
                shouldHave(sizeGreaterThan(size));
        return this;
    }

    @Step("Проверка видимости товара по имени '{name}'")
    public MainPageAssert productIsVisibleByName(String name) {
        actual.productNameList.filterBy(text(name)).first().shouldBe(visible);
        return this;
    }

    @Step("Проверка видимости нотификации и соответствие ее с ожидаемым текстом '{expectedText}'")
    public MainPageAssert notificationIs(String expectedText) {
        actual.toastMessage
                .shouldBe(visible)
                .shouldHave(text(expectedText));
        return this;
    }





    public MainPage page(){
        return actual;
    }



}

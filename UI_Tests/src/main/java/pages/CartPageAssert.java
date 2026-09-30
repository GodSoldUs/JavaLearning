package pages;

import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPageAssert extends AbstractAssert<CartPageAssert, CartPage> {

    public CartPageAssert(CartPage actual) {
        super(actual, CartPageAssert.class);
    }

    @Step("Проверка видимости кнопки оформить заказ")
    public CartPageAssert makeOrderBtnIsVisible() {
        actual.makeOrderBtn
                .should(visible);
        return this;
    }

    @Step("Проверка наличия продукта по названию '{name}' в корзине")
    public CartPageAssert cartContainsProduct(String name) {
       actual.productInCartList.
               filterBy(text(name))
               .shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Проверка общей цены >= '{maxPrice}' в корзине")
    public CartPageAssert totalPriceIsLessThanOrEqual(int maxPrice) {
        int actualPrice = Integer.parseInt(actual.totalPriceInCart.getText());

        assertThat(actualPrice)
                .as("Total price")
                .isLessThanOrEqualTo(maxPrice);
        return this;
    }

    @Step("Проверка общей цены = '{expectedPrice}' в корзине")
    public CartPageAssert totalPriceIsEqual(double expectedPrice) {
        double actualPrice = Double.parseDouble(actual.totalPriceInCart.getText());

        assertThat(actualPrice)
                .isEqualTo(expectedPrice);
        return this;
    }


    public CartPage pageCart(){
        return actual;
    }


}

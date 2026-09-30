package pages;

import com.codeborne.selenide.SelenideElement;

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

    public CartPageAssert makeOrderBtnIsVisible() {
        actual.makeOrderBtn
                .should(visible);
        return this;
    }

    public CartPageAssert cartContainsProduct(String name) {
       actual.productInCartList.
               filterBy(text(name))
               .shouldHave(sizeGreaterThan(0));


        return this;
    }


    public CartPageAssert totalPriceIsLessThanOrEqual(int maxPrice) {
        int actualPrice = Integer.parseInt(actual.totalPriceInCart.getText());

        assertThat(actualPrice)
                .as("Total price")
                .isLessThanOrEqualTo(actualPrice);
        return this;
    }

    public CartPageAssert totalPriceIsEqual(double expectedPrice) {
        double actualPrice = Double.parseDouble(actual.totalPriceInCart.getText());

        assertThat(actualPrice)
                .isEqualTo(expectedPrice);
        return this;
    }


    public CartPage pageCart(){
        return actual;
    }

    public static class AdminPageAssert extends AbstractAssert<AdminPageAssert, AdminPage> {

        public AdminPageAssert(AdminPage actual) {
            super(actual, AdminPageAssert.class);
        }

        public AdminPageAssert notificationIs(String expectedText) {
            SelenideElement toast = $x("//div[contains(@class, 'toast')]");
            toast.shouldBe(visible).shouldHave(text(expectedText));
            return this;
        }

        public AdminPage page() {
            return actual;
        }
    }
}

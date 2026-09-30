package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage extends MainPage {

    MainPage mainPage;

    public CartPage(MainPage page) {
        this.mainPage = page;
    }

    SelenideElement
            makeOrderBtn = $x("//button[@id='makeOrder']"),
            closeCart = $x("//span[@id='close-modal']"),
            totalPriceInCart = $x("//*[@id='total-price']");

    ElementsCollection
            productInCartList = $$x("//*[contains(@id, 'cart-item-')]");

    @Step("Нажать на кнопку сделать заказ")
    public MainPage clickMakeOrderBtn() {
        makeOrderBtn.click();
        return mainPage;
    }

    @Step("Нажать на крестик")
    public MainPage clickCloseCartBtn() {
        closeCart.click();
        return mainPage;
    }


    public CartPageAssert checkCart() {
        return new CartPageAssert(this);
    }


}

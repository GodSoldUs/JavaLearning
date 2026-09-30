package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    SelenideElement
            cartBtn = $x("//button[@id='open-cart-btn']"),
            adminBtn = $x("//*[contains(@class, 'btn-outline')]"),
            mainTitle = $x("//*[@id='main-title']"),
            toastMessage = $x("//div[contains(@class, 'toast')]");



    ElementsCollection
            productCardList = $$x("//*[contains(@id, 'card')]"),
            addToCardButtonList = $$x("//*[@data-action='add-to-cart']"),
            productNameList = $$x("//h4"),
            productCountInputList = $$x("//*[@type='number']");



    public MainPage inputProductCount(int index, Keys keys) {
        productCountInputList.get(index).clear();
        productCountInputList.get(index)
                .sendKeys(keys);

        return this;
    }

    public MainPage inputProductCount(int index, String text) {
        productCountInputList.get(index).clear();
        productCountInputList.get(index)
                .sendKeys(text);
        return this;
    }

    public MainPage addToCartBtnClick(int index) {
        addToCardButtonList.get(index)
                .click();
        return this;
    }





    public AuthPage clickAdminButton() {
        adminBtn.click();
        return new AuthPage();
    }




    public MainPage addProductToCartById(int productId) {
        getAddToCartButtonById(productId).click();
        return this;
    }



    public SelenideElement getProductCardById(int productId) {
        return $x("//div[@data-id='" + productId + "']");
    }

    /*public SelenideElement getProductCard(int productId) {
        return getProductCardById(productId);
    }*/

    public SelenideElement getAddToCartButtonById(int productId) {
        SelenideElement product = getProductCardById(productId);
        return product.$x(".//button[@data-action='add-to-cart']");
    }

    public MainPage addToCartButtonByIdClick(int productId) {
        getAddToCartButtonById(productId).click();
        return this;
    }



    public SelenideElement getProductQuantityInputById(int productId) {
        return $x("//input[@type='number' and @id='q-" + productId + "']");
    }

    public MainPage setProductQuantity(int productId, int quantity) {
        SelenideElement input = getProductQuantityInputById(productId);
        input.clear();
        input.sendKeys(String.valueOf(quantity));
        return this;
    }


    public String getProductNameById(int productId) {
        SelenideElement product = getProductCardById(productId);
        return product
                .$x(".//h4")
                .shouldBe(visible)
                .getText();
    }

    public int getProductPrice(int productId) {
        SelenideElement product = getProductCardById(productId);
        String price = product
                .$x(".//button[@data-action='add-to-cart']")
                .getAttribute("data-price");

        return Integer.parseInt(price);
    }






//    public CartPopup cartPopup() {
//        return new CartPopup();
//    }
//
//    public class CartPopup {
//        SelenideElement
//                makeOrderBtn = $x("//button[@id='makeOrder']"),
//                closeCart = $x("//span[@id='close-modal']"),
//                totalPriceInCart = $x("//*[@id='total-price'");
//
//        ElementsCollection
//                productInCartList = $$x("//*[contains(@id, 'cart-item-')]");
//
//
//
//        public MainPage clickCloseCartBtn() {
//            closeCart.click();
//            return getThis();
//        }
//    }


    public CartPage cartBtnClick() {
        cartBtn.click();
        return new CartPage(this);
    }

    private MainPage getThis() {
        return this;
    }


    public MainPageAssert check() {
        return new MainPageAssert(this);
    }

}



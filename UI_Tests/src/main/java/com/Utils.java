package com;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Utils {
    public static final SelenideElement adminBtn =
            $x("//*[contains(@class, 'btn-outline')]");

    public static final SelenideElement bucketBtn =
            $x("//button[@id='open-cart-btn']");


    public static final SelenideElement returnToMainBtn =
            $x("//a[@href='/']");

    public static final SelenideElement productList  =
            $("#products-list");

    public static final SelenideElement addNewProductBtn  =
            $("#add-btn");

    public static final SelenideElement nameField  =
            $x("//input[@id='n-name']");

    public static final SelenideElement priceField  =
            $x("//input[@id='n-price']");

    public static final SelenideElement itemInBucket =
            $x("//div[@id='cart-items']"
                    + "//div[contains(@class, 'cart-item')]");


    public static final SelenideElement makeOrder =
            $x("//button[@id='makeOrder']");




    public static void addToCart(SelenideElement product) {
        product
                .$x(".//button[@data-action='add-to-cart']")
                .shouldBe(visible)
                .click();
    }

    public static String getProductName(SelenideElement product) {
        return product
                .$x(".//h4")
                .shouldBe(visible)
                .getText();
    }

    public static int getProductPrice(SelenideElement product) {
        String price = product
                .$x(".//button[@data-action='add-to-cart']")
                .getAttribute("data-price");

        return Integer.parseInt(price);
    }

    public static SelenideElement firstProduct() {
        return productList
                .$x(".//div[contains(@class, 'product-card')][1]")
                .shouldBe(visible);
    }

    public static SelenideElement productFromId(int id) {
        return productList.$x("./div[@data-id='%d']".formatted(id));
    }

    public static SelenideElement changeNameField(int id) {
        return $x("//input[@id='nm-%d']".formatted(id));
    }

    public static SelenideElement changePriceField(int id) {
        return $x("//input[@id='pr-%d']".formatted(id));
    }

    public static  SelenideElement saveChangesAdmin(int id) {
        return $x("//button[@data-id='%d'][@data-action='update']".formatted(id));
    }





    public static void signIn(String login, String password) {

        adminBtn.click();

        SelenideElement loginField = $(By.id("username"));
        loginField.sendKeys(login);

        SelenideElement passwordField = $(By.id("password"));
        passwordField.sendKeys(password);

        SelenideElement singInBtn = $x("//button[@type='submit']");
        singInBtn.click();




    }
}

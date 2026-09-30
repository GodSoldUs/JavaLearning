package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AdminPage {
    SelenideElement
            nameInput = $x("//input[@id='n-name']"),
            priceInput = $x("//input[@id='n-price']"),
            addButton = $x("//button[@id='add-btn']"),
            returnToMainBtn = $x("//a[contains(@href, '/')]");

    public AdminPage addProduct(String name, String price) {
        nameInput.sendKeys(name);
        priceInput.sendKeys(price);
        addButton.click();
        return this;
    }

    public AdminPage editProduct(int productId, String newName, String newPrice) {
        $x("//input[@id='nm-" + productId + "']").sendKeys(newName);
        $x("//input[@id='pr-" + productId + "']").sendKeys(newPrice);
        $x("//button[@data-id='" + productId + "']").click();
        return this;
    }

    public MainPage returnToMainPage() {
        returnToMainBtn.click();
        return new MainPage();
    }

    public CartPageAssert.AdminPageAssert check() {
        return new CartPageAssert.AdminPageAssert(this);
    }

}

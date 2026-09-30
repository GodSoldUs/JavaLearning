package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AuthPage {


    SelenideElement singInBtn = $x("//button[@type='submit']");
    SelenideElement loginField = $x("//input[@id='username']");
    SelenideElement passwordField = $x("//input[@id='password']");

    @Step("Вводиться логин")
    public AuthPage setLogin(String login) {
        loginField
                .sendKeys(login);
        return this;
    }

    @Step("Вводиться пароль")
    public AuthPage setPassword(String password) {
        passwordField
                .sendKeys(password);
        return this;
    }

    @Step("Нахать на кнопну войти")
    public AdminPage clickSingInBtn() {
        singInBtn
                .click();
        return new AdminPage();
    }

    @Step("Перейти к проверкам страницы авторизации")
    public AuthPageAssert check() {
        return new AuthPageAssert(this);
    }

    public SelenideElement getSingInBtn() { return singInBtn; }
    public SelenideElement getLoginField() { return loginField; }
    public SelenideElement getPasswordField() { return passwordField; }
}

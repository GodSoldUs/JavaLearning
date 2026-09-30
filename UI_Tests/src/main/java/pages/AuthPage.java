package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AuthPage {


    SelenideElement singInBtn = $x("//button[@type='submit']");
    SelenideElement loginField = $x("//input[@id='username']");
    SelenideElement passwordField = $x("//input[@id='password']");

    public AuthPage setLogin(String login) {
        loginField
                .sendKeys(login);
        return this;
    }

    public AuthPage setPassword(String password) {
        passwordField
                .sendKeys(password);
        return this;
    }

    public AdminPage clickSingInBtn() {
        singInBtn
                .click();
        return new AdminPage();
    }

    public AuthPageAssert check() {
        return new AuthPageAssert(this);
    }

    public SelenideElement getSingInBtn() { return singInBtn; }
    public SelenideElement getLoginField() { return loginField; }
    public SelenideElement getPasswordField() { return passwordField; }
}

package pages;

import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.Condition.visible;

public class AuthPageAssert extends AbstractAssert <AuthPageAssert,AuthPage> {

    public AuthPageAssert(AuthPage actual) {
        super(actual, AuthPageAssert.class);
    }

    @Step("Прверка наличия элементов")
    public AuthPageAssert isLoaded() {
        actual.getSingInBtn().shouldBe(visible);
        actual.getLoginField().shouldBe(visible);
        actual.getPasswordField().shouldBe(visible);
        return this;
    }

    public AuthPage page() {
        return actual;
    }




}

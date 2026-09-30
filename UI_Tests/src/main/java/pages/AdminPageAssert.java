package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.AbstractAssert;


import static com.codeborne.selenide.Selenide.$x;

public class AdminPageAssert extends AbstractAssert<AdminPageAssert, AdminPage> {

    public AdminPageAssert(AdminPage actual) {
        super(actual, AdminPageAssert.class);
    }

    public AdminPageAssert notificationIs(String expectedText) {
        isNotNull();
        SelenideElement toast = $x("//div[contains(@class, 'toast')]");
        toast.shouldBe(Condition.visible).shouldHave(Condition.text(expectedText));
        return this;
    }

    public AdminPage page() {
        return actual;
    }
}

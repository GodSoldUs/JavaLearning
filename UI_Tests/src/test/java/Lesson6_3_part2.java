import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import rest.endpoints.*;
import DTO.*;
import io.restassured.response.Response;
import static com.Utils.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Tag("UI-test")
public class Lesson6_3_part2 {

    static GoodsApi goodsApi = new GoodsApi();

    private static int createdId;





    @BeforeAll
    static void setup() {

        open("http://localhost:8080");
        signIn("admin", "secret123");
        $x("//div[contains(@class, 'container')]//h1")
                .shouldBe(visible)
                .shouldHave(text("SmartShop Admin"));
    }



    @AfterAll
    static void tearDown() {
        cookies().clear();
        closeWebDriver();
    }






    @DisplayName("Task3.3")
    @Order(1)
    @Test
    void addProductAdmin() {

        String name = "NewName" + new Random().nextInt(0,100);
        String price = "150";


        nameField.sendKeys(name);
        priceField.sendKeys(price);
        addNewProductBtn.click();


        $x("//div[contains(@class, 'toast')]")
                .shouldBe(visible)
                .shouldHave(text("Товар успешно добавлен!"));

        createdId = Integer.parseInt(Selenide.$x("//tbody/tr[last()]/td").getText());


    }

    @DisplayName("Task3.4")
    @Test
    void changeProductAdmin() {

        String name = "changedName" + new Random().nextInt(0,100);
        String price = "33";

        changePriceField(createdId).setValue(price);
        changeNameField(createdId).setValue(name);
        saveChangesAdmin(createdId).click();


        returnToMainBtn.click();

        productList.$x(
                        ".//div[@data-name='" + name + "'" +
                                " and @data-price='" + price + "']"
                )
                .shouldBe(visible);





    }





}

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

@Tag("UI-test")
public class Lesson6_3_part1 {

    static GoodsApi goodsApi = new GoodsApi();

    private static final List<Integer> createdId = new ArrayList<>();

    static double totalPrice;



    @BeforeAll
    static void setup() {


        DTO.ProductRequestDto product_1 = new ProductRequestDto("Banana" + new Random().nextInt(100), 30.1);
        DTO.ProductRequestDto product_2 = new ProductRequestDto("Apple" + new Random().nextInt(100), 12.01);
        DTO.ProductRequestDto product_3 = new ProductRequestDto("Pineapple" + new Random().nextInt(100), 50.001);

        totalPrice = product_1.price() + product_2.price() +product_3.price();
        Response response_1 = goodsApi.createGood(product_1);
        createdId.add(response_1.jsonPath().getInt("data.id"));

        Response response_2 = goodsApi.createGood(product_2);
        createdId.add(response_2.jsonPath().getInt("data.id"));

        Response response_3 = goodsApi.createGood(product_3);
        createdId.add(response_3.jsonPath().getInt("data.id"));

        Selenide.open("http://localhost:8080");
    }



    @AfterAll
    static void tearDown() {
        for (int productId : createdId) {
            goodsApi.deleteGood(productId);
        }
        cookies().clear();
        closeWebDriver();
    }






    @DisplayName("Task3.1")
    @Test
    void buyThreeProducts() {
        for (int productId : createdId) {
            SelenideElement product =  productFromId(productId);
            addToCart(product);
        }
        bucketBtn.click();
        $x("//button[@id ='makeOrder']")
                .shouldBe(visible);

        makeOrder.click();

        $x("//div[contains(@class, 'toast')][last()]")
                .shouldBe(visible)
                .shouldHave(text("Заказ принят в обработку!"));

    }

    @DisplayName("Task3.2")
    @Test
    void trueTotalPrice() {
        for (int productId : createdId) {
            SelenideElement product =  productFromId(productId);
            addToCart(product);
        }
        bucketBtn.click();
        $x("//button[@id ='makeOrder']")
                .shouldBe(visible);


        $("#total-price")
                .shouldHave(
                        text(String.valueOf(totalPrice))
                );


    }

}

import DTO.ProductRequestDto;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.ConfigProvider;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import pages.AuthPage;
import rest.endpoints.GoodsApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("UI-test")
public class Lesson8_Tests extends BaseTest {


    static GoodsApi goodsApi = new GoodsApi();

    private static final List<Integer> createdId = new ArrayList<>();

    static double totalPrice;

    @BeforeAll
    static void addProduct() {

        DTO.ProductRequestDto product_1 = new ProductRequestDto("Banana", 75.0);
        DTO.ProductRequestDto product_2 = new ProductRequestDto("Apple", 60.0);
        DTO.ProductRequestDto product_3 = new ProductRequestDto("Pineapple", 150.0);

        totalPrice = product_1.price() + product_2.price() +product_3.price();
        Response response_1 = goodsApi.createGood(product_1);
        createdId.add(response_1.jsonPath().getInt("data.id"));

        Response response_2 = goodsApi.createGood(product_2);
        createdId.add(response_2.jsonPath().getInt("data.id"));

        Response response_3 = goodsApi.createGood(product_3);
        createdId.add(response_3.jsonPath().getInt("data.id"));


    }

    @Order(1)
    @Test
    @DisplayName("Task2.1")
    void makeOrderTest() {

        mainPage
                .check()
                    .mainTitleIsVisible()
                    .cartButtonIsVisible()
                    .adminButtonIsVisible()
                    .productListIsGreaterThen(0)
                .page()
                    .addToCartButtonByIdClick(createdId.get(0))
                    .addToCartButtonByIdClick(createdId.get(1))
                    .addToCartButtonByIdClick(createdId.get(2))
                    .cartBtnClick()
                .checkCart()
                    .cartContainsProduct("Banana")
                    .makeOrderBtnIsVisible()
                    .totalPriceIsLessThanOrEqual(300)
                .pageCart()
                .clickMakeOrderBtn()
                .check()
                .notificationIs("Заказ принят в обработку!");
        sleep(1000);

    }

    @Order(2)
    @Test
    @DisplayName("Task2.2")
    void totalPriceInCartTest() {
        sleep(1000);
        mainPage
                .check()
                    .mainTitleIsVisible()
                    .productListIsGreaterThen(0)
                .page()
                    .addToCartButtonByIdClick(createdId.get(0))
                    .addToCartButtonByIdClick(createdId.get(1))
                    .addToCartButtonByIdClick(createdId.get(2))
                            .cartBtnClick()
                                    .checkCart()
                                            .totalPriceIsEqual(totalPrice)
                                                    .pageCart();




        sleep(1000);
    }

    @Order(3)
    @DisplayName("Task3")
    @Test
    void addProductInAdmin() {
        sleep(1000);
        String newName = "NewYork" + new Random().nextInt(100);
        mainPage
                .check()
                .mainTitleIsVisible()
                .adminButtonIsVisible()
                .page()
                .clickAdminButton();
        new AuthPage()
                .setLogin(ConfigProvider.CONFIG.login())
                .setPassword(ConfigProvider.CONFIG.password())
                .clickSingInBtn()
                .addProduct(newName, "123")
                .check()
                .notificationIs("Товар успешно добавлен!");
        sleep(1000);
    }

    @Order(4)
    @DisplayName("Task4")
    @Test
    void updateProductAndCheckTest() {
        sleep(1000);
        int targetId = createdId.get(0);
        String updatedName = "Bumblbe";

                mainPage.clickAdminButton();
        new AuthPage()
                .setLogin(ConfigProvider.CONFIG.login())
                .setPassword(ConfigProvider.CONFIG.password())
                .clickSingInBtn()
                .editProduct(targetId, updatedName, "990")
                .returnToMainPage()
                .check()
                .productIsVisibleByName(updatedName);
        sleep(1000);
    }

    @AfterEach
    void cleanState() {

        if (WebDriverRunner.hasWebDriverStarted()) {
            Selenide.clearBrowserCookies();
            Selenide.clearBrowserLocalStorage();
        }
    }

    @AfterAll
    static void tearDown() {
        for (int productId : createdId) {
            goodsApi.deleteGood(productId);
        }
        cookies().clear();
        closeWebDriver();
    }
}

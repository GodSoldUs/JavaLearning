import DTO.ProductRequestDto;
import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DragAndDropOptions;
import com.codeborne.selenide.SelenideElement;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import rest.endpoints.GoodsApi;

import java.time.Duration;
import java.util.Objects;
import java.util.Random;

import static com.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static helpMethods.TestDataFactory.randGoodAndPrice;
import config.ConfigProvider;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


@Tag("UI-test")
public class Lesson7 extends BaseTest {

    static GoodsApi goods = new GoodsApi();
    private static int createdId;

    SelenideElement productDnd = productFromId(createdId);
    String productName = getProductName(productDnd);

    @BeforeAll
    static void setup() {
        ConfigProvider.printConfigParams();
        Configuration.timeout =
                ConfigProvider.CONFIG.timeout();

        DTO.ProductRequestDto product = new ProductRequestDto(
                ConfigProvider.CONFIG.startName(),
                ConfigProvider.CONFIG.startPrice()
        );
        Response response = goods.createGood(product);
        createdId = Integer.parseInt(response.jsonPath().getString("data.id"));
        open(ConfigProvider.CONFIG.Url());

    }

    @Order(1)
    @DisplayName("DnD Test")
    @Test
    void DndTest() {


        productDnd
                .shouldBe(visible)
                .dragAndDrop(
                        DragAndDropOptions.to(bucketBtn)
                );

        $x("//div[contains(@class, 'toast')]")
                .shouldBe(visible)
                .shouldHave(
                        matchText(".*добавлен в корзину\\.?$")
                );


        sleep(1000);

        bucketBtn
                .shouldBe(visible)
                .click();

        itemInBucket
                .shouldBe(visible)
                .shouldHave(text(productName));

        closeBucket.click();
    }

    @Order(2)
    @Test
    void anotherDnd() {

        actions().moveToElement(productDnd)
                .clickAndHold()
                .pause(1000)
                .moveToElement(bucketBtn)
                .release()
                .perform();

        $x("//div[contains(@class, 'toast')]")
                .shouldBe(visible)
                .shouldHave(
                        matchText(".*добавлен в корзину\\.?$")
                );




        bucketBtn
                .shouldBe(visible)
                .click();

        itemInBucket
                .shouldBe(visible)
                .shouldHave(text(productName));

        closeBucket.click();
    }


    @DisplayName("Task2")
    @Test
    void deleteFormBucket() {
        bucketBtn.click();


        SelenideElement cartItem = $x(
                "//div[@id='cart-items']" +
                        "/div[@id='cart-item-" + createdId + "']"
        );

        cartItem
                .$x("./button[@data-action='remove']")
                .shouldBe(visible)
                .click();


        sleep(2000);

        cartItem
                .shouldNot(exist);
    }




    @AfterAll
    static void tearDown() {
        goods.deleteGood(createdId);
        cookies().clear();
        closeWebDriver();
    }


}

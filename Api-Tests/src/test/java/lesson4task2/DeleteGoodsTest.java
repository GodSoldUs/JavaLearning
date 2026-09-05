package lesson4task2;

import DTO.ProductRequestDto;
import io.restassured.response.Response;

import org.junit.jupiter.api.*;
import rest.assertions.BasicApiAssert;
import rest.endpoints.GoodsApi;

import java.util.Random;

import static helpMethods.TestDataFactory.randGoodAndPrice;

@DisplayName("[Delete]/goods/id")
@Tag("Api-test")
public class DeleteGoodsTest {

    @BeforeEach
    void start() {
        System.out.println("========================\n" + "Test method start");
    }

    @AfterEach
    void finish() {
        System.out.println("Test method end\n" + "========================");
    }

    GoodsApi goodsApi = new GoodsApi();

    @DisplayName("Delete /goods/id HappyFlow")
    @Test
    void deleteGoodTest() {

        DTO.ProductRequestDto product = new ProductRequestDto(
                config.ConfigProvider.CONFIG.startName(),
                config.ConfigProvider.CONFIG.startPrice()
        );
        Response responsePost = goodsApi.createGood(product);

        BasicApiAssert.assertThat(responsePost)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        Response responseDelete = goodsApi.deleteGood(responsePost.jsonPath().getInt("data.id"));
        BasicApiAssert.assertThat(responseDelete)
                .statusCodeIsEquals(200);


        Response responseGet = goodsApi.getGoodFromId(responsePost.jsonPath().getInt("data.id"));
        BasicApiAssert.assertThat((responseGet))
                .statusCodeIsEquals(404);


    }


    @DisplayName("Delete /goods/id 404 Negative id")
    @Test
    void deleteGoodNegativeIdTest() {

        Response responseDelete = goodsApi.deleteGood(-1);
        BasicApiAssert.assertThat(responseDelete)
                .statusCodeIsEquals(404);

    }

    @DisplayName("Delete /goods/id 404 Unknown id")
    @Test
    void deleteGoodUnknownIdTest() {

        Response responseDelete = goodsApi.deleteGood(new Random().nextInt(100, 200));
        BasicApiAssert.assertThat(responseDelete)
                .statusCodeIsEquals(404);

    }

    @DisplayName("Delete /goods/id 400 String id")
    @Test
    void deleteGoodStringIdTest() {

        Response responseDelete = goodsApi.deleteGoodString("dsjn");
        BasicApiAssert.assertThat(responseDelete)
                .statusCodeIsEquals(400);

    }

    @DisplayName("Delete /goods/id 401 No Auth")
    @Test
    void deleteGoodWithoutAuthTest() {

        Response responseDelete = goodsApi.deleteGoodWithoutAuth(1);
        BasicApiAssert.assertThat(responseDelete)
                .statusCodeIsEquals(401);

    }














}

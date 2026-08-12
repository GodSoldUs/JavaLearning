package lesson4task2;

import io.restassured.response.Response;

import org.junit.jupiter.api.*;
import rest.assertions.BasicApiAssert;
import rest.endpoints.GoodsApi;

import java.util.Random;

import static helpMethods.TestDataFactory.randGoodAndPrice;

@DisplayName("[Get]/goods/id")
@Tag("Api-test")
public class GetGoodsTest {
    private Integer createdId;

    @AfterEach
    void deleteCreatedGood() {
        if (createdId != null) {
            Response deleteResponse =
                    goodsApi.deleteGood(createdId);

            deleteResponse.then()
                    .log()
                    .all();

            createdId = null;
        }
    }

    @BeforeEach
    void start() {
        System.out.println("========================\n" + "Test method start");
    }

    @AfterEach
    void finish() {
        System.out.println("Test method end\n" + "========================");
    }

    GoodsApi goodsApi = new GoodsApi();

    @DisplayName("GET /goods/id HappyFlow")
    @Test
    void getTest() {
        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        Response responsePost = goodsApi.createGood(product);

        BasicApiAssert.assertThat(responsePost)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        createdId = responsePost.jsonPath().getInt("data.id");
        Response response = goodsApi.getGoodFromId(createdId);
        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(200)
                .fieldIsEquals("id", String.valueOf(createdId))
                .fieldIsEquals("name", product.name())
                .fieldIsEquals("price", String.valueOf(product.price()));
    }

    @DisplayName("GET /goods/id 404")
    @RepeatedTest(10)
    void getTestUnknownId() {
        int productId = new Random().nextInt(100, 200);
        Response response = goodsApi.getGoodFromId(productId);
        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(404);
    }

    @DisplayName("GET /goods/id 404 Negative id")
    @Test
    void getTestNegativeId() {
        int productId = -1;
        Response response = goodsApi.getGoodFromId(productId);
        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(404);
    }

    @DisplayName("GET /goods/id 400. id String")
    @Test
    void getTestStringId() {
        String productId = "sffe";
        Response response = goodsApi.getGoodFromIdString(productId);
        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(400);
    }




}

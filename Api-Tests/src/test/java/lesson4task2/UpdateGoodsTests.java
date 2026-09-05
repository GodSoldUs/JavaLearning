package lesson4task2;

import DTO.ProductRequestDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import rest.assertions.BasicApiAssert;
import rest.endpoints.GoodsApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static helpMethods.TestDataFactory.randGoodAndPrice;

@DisplayName("[Patch]/goods/id")
@Tag("Api-test")
public class UpdateGoodsTests {


    @BeforeEach
    void start() {
        System.out.println("========================\n" + "Test method start");
    }

    @AfterEach
    void finish() {
        System.out.println("Test method end\n" + "========================");
    }

    GoodsApi goodsApi = new GoodsApi();

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

    @DisplayName("PATCH /goods/id HappyFlow both")
    @Test
    void updateGoodsTest() {
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


        createdId = responsePost.jsonPath().getInt("data.id");

        String expectedName = "Updated product" + new Random().nextInt();
        double price = new Random().nextDouble(1, 500);
        Double expectedPrice = Math.round(price * 1000.0) / 1000.0;
        Map<String, Object> updateBody = Map.of(
                "name", expectedName,
                "price", expectedPrice
        );

        Response responseUpdate = goodsApi.updateGood(createdId, updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(200)
                .fieldIsEquals("id", String.valueOf(createdId))
                .fieldIsEquals("name", expectedName)
                .fieldIsEquals("price", String.valueOf(expectedPrice));


        Response responseGet = goodsApi.getGoodFromId(createdId);

        BasicApiAssert.assertThat(responseGet)
                .statusCodeIsEquals(200)
                .fieldIsEquals("id", String.valueOf(createdId))
                .fieldIsEquals("name", expectedName)
                .fieldIsEquals("price", String.valueOf(expectedPrice));
    }


    @DisplayName("PATCH /goods/id HappyFlow Update name")
    @Test
    void updateGoodsNameTest() {
        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        Response responsePost = goodsApi.createGood(product);

        BasicApiAssert.assertThat(responsePost)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        createdId = responsePost.jsonPath().getInt("data.id");

        String expectedName = "Updated product" + new Random().nextInt();

        Map<String, Object> updateBody = Map.of(
                "name", expectedName
        );



        Response responseUpdate = goodsApi.updateGood(createdId, updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(200)
                .fieldIsEquals("id", String.valueOf(createdId))
                .fieldIsEquals("name", expectedName);


        Response responseGet = goodsApi.getGoodFromId(createdId);

        BasicApiAssert.assertThat(responseGet)
                .statusCodeIsEquals(200)
                .fieldIsEquals("id", String.valueOf(createdId))
                .fieldIsEquals("name", expectedName)
                .fieldIsEquals("price", responseGet.jsonPath().getString("price"));


    }

    @DisplayName("PATCH /goods/id 404.")
    @Test
    void updateGoodsUnknownIdTest() {
        Map<String, Object> updateBody = Map.of(
                "name", "New name",
                "price", 0.0
        );



        Response responseUpdate = goodsApi.updateGood(101, updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(404);
    }

    @DisplayName("PATCH /goods/id 400. Update only price")
    @Test
    void updateGoodsPriceTest() {
        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        Response responsePost = goodsApi.createGood(product);

        BasicApiAssert.assertThat(responsePost)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        createdId = responsePost.jsonPath().getInt("data.id");

        double price = new Random().nextDouble(1, 500);
        Double expectedPrice = Math.round(price * 1000.0) / 1000.0;

        Map<String, Object> updateBody = Map.of(
                "price", expectedPrice
        );



        Response responseUpdate = goodsApi.updateGood(createdId, updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(400);
    }




    @DisplayName("PATCH /goods/id Negative id 404.")
    @Test
    void updateGoodsIdTest() {
        Map<String, Object> updateBody = Map.of(
                "name", "New name",
                "price", 0.0
        );



        Response responseUpdate = goodsApi.updateGood(-1, updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(404);
    }

    @DisplayName("PATCH /goods/id String id  400.")
    @Test
    void updateGoodsNegativeIdTest() {
        Map<String, Object> updateBody = Map.of(
                "name", "New name",
                "price", 0.0
        );



        Response responseUpdate = goodsApi.updateGoodStringId("djdj", updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(400);
    }

    @DisplayName("PATCH /goods/id 400 Name exist")
    @Test
    void updateGoodsNameExistTest() {
        DTO.ProductRequestDto product1 = new ProductRequestDto("Yabloko1", 19.4);
        Response responsePost1 = goodsApi.createGood(product1);

        BasicApiAssert.assertThat(responsePost1)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


         int id_first = responsePost1.jsonPath().getInt("data.id");

        DTO.ProductRequestDto product2 = new ProductRequestDto("Yabloko2", 19.4);
        Response responsePost2 = goodsApi.createGood(product2);

        BasicApiAssert.assertThat(responsePost2)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        createdId = responsePost2.jsonPath().getInt("data.id");


        Map<String, Object> updateBody = Map.of(
                "name", product2.name()
        );

        Response responseUpdate = goodsApi.updateGood(id_first, updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(400);

        goodsApi.deleteGood(id_first);
    }


    @DisplayName("PATCH /goods/id 400 NegativePrice")
    @Test
    void updateGoodsNegativePriceTest() {
        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        Response responsePost = goodsApi.createGood(product);

        BasicApiAssert.assertThat(responsePost)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        createdId = responsePost.jsonPath().getInt("data.id");


        Map<String, Object> updateBody = Map.of(
                "name", "product123",
                "price", -1
        );

        Response responseUpdate = goodsApi.updateGood(createdId, updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(400);

    }

    @DisplayName("PATCH /goods/id 400 Null name")
    @Test
    void updateGoodsNullNameTest() {
        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        Response responsePost = goodsApi.createGood(product);

        BasicApiAssert.assertThat(responsePost)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        createdId = responsePost.jsonPath().getInt("data.id");


        Map<String, Object> updateBody = new HashMap<>();

        updateBody.put("name", null);


        Response responseUpdate = goodsApi.updateGood(createdId, updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(400);

    }

    @DisplayName("PATCH /goods/id 400 Null Price")
    @Test
    void updateGoodsNullPriceTest() {
        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        Response responsePost = goodsApi.createGood(product);

        BasicApiAssert.assertThat(responsePost)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        createdId = responsePost.jsonPath().getInt("data.id");


        Map<String, Object> updateBody = new HashMap<>();

        updateBody.put("name", "product1234");
        updateBody.put("price", null);

        Response responseUpdate = goodsApi.updateGood(createdId, updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(400);

    }


    @DisplayName("PATCH /goods/id 400 Empty name")
    @Test
    void updateGoodsEmptyNameTest() {
        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        Response responsePost = goodsApi.createGood(product);

        BasicApiAssert.assertThat(responsePost)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        createdId = responsePost.jsonPath().getInt("data.id");


        Map<String, Object> updateBody = new HashMap<>();

        updateBody.put("name", "");


        Response responseUpdate = goodsApi.updateGood(createdId, updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(400);

    }

    @DisplayName("PATCH /goods/id 400 Without name")
    @Test
    void updateGoodsWithoutNameTest() {
        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        Response responsePost = goodsApi.createGood(product);

        BasicApiAssert.assertThat(responsePost)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        createdId = responsePost.jsonPath().getInt("data.id");


        Map<String, Object> updateBody = new HashMap<>();

        updateBody.put("price", 78.5);


        Response responseUpdate = goodsApi.updateGood(createdId, updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(400);

    }

    @DisplayName("PATCH /goods/id 400 Int name")
    @Test
    void updateGoodsIntNameTest() {
        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        Response responsePost = goodsApi.createGood(product);

        BasicApiAssert.assertThat(responsePost)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        createdId = responsePost.jsonPath().getInt("data.id");


        Map<String, Object> updateBody = new HashMap<>();

        updateBody.put("name", 123);


        Response responseUpdate = goodsApi.updateGood(createdId, updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(400);

    }

    @DisplayName("PATCH /goods/id 400 String Price")
    @Test
    void updateGoodsStringPriceTest() {
        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        Response responsePost = goodsApi.createGood(product);

        BasicApiAssert.assertThat(responsePost)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        createdId = responsePost.jsonPath().getInt("data.id");


        Map<String, Object> updateBody = new HashMap<>();

        updateBody.put("price", "1af");


        Response responseUpdate = goodsApi.updateGood(createdId, updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(400);

    }


    @DisplayName("PATCH /goods/id 401 Without auth")
    @Test
    void updateGoodsWithoutAuthTest() {
        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        Response responsePost = goodsApi.createGood(product);

        BasicApiAssert.assertThat(responsePost)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        createdId = responsePost.jsonPath().getInt("data.id");


        Map<String, Object> updateBody = new HashMap<>();

        updateBody.put("name", "qer");


        Response responseUpdate = goodsApi.updateGoodWithoutAuth(createdId, updateBody);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(401);

    }

    @DisplayName("PATCH /goods/id 400 Empty body")
    @Test
    void updateGoodsEmptyBodyTest() {
        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        Response responsePost = goodsApi.createGood(product);

        BasicApiAssert.assertThat(responsePost)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        createdId = responsePost.jsonPath().getInt("data.id");


        Map<String, Object> updateBody = new HashMap<>();

        updateBody.put("name", "ewge");


        Response responseUpdate = goodsApi.updateGoodWithoutBody(createdId);

        BasicApiAssert.assertThat(responseUpdate)
                .statusCodeIsEquals(400);

    }


}

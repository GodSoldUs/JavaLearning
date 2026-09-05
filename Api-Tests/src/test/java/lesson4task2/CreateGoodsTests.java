package lesson4task2;

import DTO.ProductRequestDto;
import DTO.ProductWithoutNameDto;
import DTO.ProductWithoutPriceDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import rest.assertions.BasicApiAssert;
import rest.endpoints.GoodsApi;
import config.ConfigProvider;


import java.util.Map;
import java.util.Random;

import static helpMethods.TestDataFactory.randGoodAndPrice;


@DisplayName("[POST]/goods/add")
@Tag("Api-test")
public class CreateGoodsTests {

    @BeforeAll
    static void setup() {
        ConfigProvider.printConfigParams();
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

    @DisplayName("POST /goods/add HappyFlow")
    @Test
    void addGoodTest() {

        DTO.ProductRequestDto product = new ProductRequestDto(
                ConfigProvider.CONFIG.startName(),
                ConfigProvider.CONFIG.startPrice()
        );

        Response response = goodsApi.createGood(product);

        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        createdId = response.jsonPath().getInt("data.id");
    }

    @DisplayName("POST /goods/add 400. Duplicate name")
    @Test
    void addGoodDuplicateNameTest() {

        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        goodsApi.createGood(product);
        Response response = goodsApi.createGood(product);



        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(400);
    }

    @DisplayName("POST /goods/add 401. Without auth")
    @Test
    void addGoodWithoutAuthTest() {

        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        Response response = goodsApi.createGoodWithoutAuth(product);



        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(401);
    }


    @DisplayName("POST /goods/add 200. WithoutPrice")
    @Test
    void addGoodWithoutPriceTest() {

        String name = "Lemon" + new Random().nextInt();
        DTO.ProductWithoutPriceDto product = new ProductWithoutPriceDto(name);
        Response response = goodsApi.createGoodBadRequest(product);



        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(200);

        createdId = response.jsonPath().getInt("data.id");


        Response responseGet = goodsApi.getGoodFromId(createdId);
        BasicApiAssert.assertThat(responseGet)
                .fieldIsEquals("id", String.valueOf(createdId))
                .fieldIsEquals("name", name);
    }


    @Test
    @DisplayName("POST /goods/add 400. Negative price")
    void addGoodWithNegativePrice() {
        DTO.ProductRequestDto product = new ProductRequestDto("Strawberry", -1.0);
        Response response = goodsApi.createGood(product);



        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(400);
    }

    @Test
    @DisplayName("POST /goods/add 400. Without name")
    void addGoodWithoutName() {
        DTO.ProductWithoutNameDto product = new ProductWithoutNameDto(10.0);
        Response response = goodsApi.createGoodBadRequest(product);



        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(400);
    }

    @DisplayName("POST /goods/add 400. Price = null")
    @Test
    void addGoodPriceNullTest() {
        String name = "Pineapple" + new Random().nextInt();
        DTO.ProductRequestDto product = new ProductRequestDto(name, null);
        Response response = goodsApi.createGood(product);

        createdId = response.jsonPath().getInt("data.id");

        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(400);

    }

    @DisplayName("POST /goods/add 400. Name = null")
    @Test
    void addGoodNameNullTest() {

        DTO.ProductRequestDto product = new ProductRequestDto(null,0.0);
        Response response = goodsApi.createGood(product);



        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(400);

    }

    @Test
    @DisplayName("POST /goods/add 400 name not string")
    void addGoodWithInvalidNameType() {
        Map<String, Object> requestBody = Map.of(
                "name", new Random().nextInt(100),
                "price", 100.0
        );

        Response response =
                goodsApi.createGoodBadRequest(requestBody);


        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(400);

        createdId = response.jsonPath().getInt("data.id");
    }


    @Test
    @DisplayName("POST /goods/add price not double")
    void addGoodWithInvalidPriceType() {
        String name = "Watermelon" + new Random().nextInt();

        Map<String, Object> requestBody = Map.of(
                "name", name,
                "price", "100"
        );

        Response response =
                goodsApi.createGoodBadRequest(requestBody);




        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(400);
    }

    @DisplayName("POST /goods/add 400. Name empty")
    @Test
    void addGoodEmptyNameTest() {

        DTO.ProductRequestDto product = new ProductRequestDto("", 8.0);
        Response response = goodsApi.createGood(product);



        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(400);


    }
}





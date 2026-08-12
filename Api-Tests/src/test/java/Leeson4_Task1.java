import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rest.assertions.BasicApiAssert;
import rest.endpoints.GoodsApi;
import rest.endpoints.Urls;

import java.util.Random;

import static helpMethods.TestDataFactory.randGoodAndPrice;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class Leeson4_Task1 {

    @BeforeEach
    void start() {
        System.out.println("========================\n" + "Test method start");
    }

    @AfterEach
    void finish() {
        System.out.println("Test method end\n" + "========================");
    }

    private final RequestSpecification basicRQ = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080/goods")
            .log(LogDetail.ALL)
            .build();

    private final RequestSpecification basicRQAuth = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080/goods")
            .setAuth(RestAssured.basic("admin", "secret123"))
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    @DisplayName("getGoodsGivenTest(4.1.1)")
    @Test
    void getGivenTest() {
        given()
                .spec(basicRQ)
                .when()
                .get(Urls.LIST)
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("goods", is(empty()));
    }

    @DisplayName("addGoodsGivenTest(4.1.3)")
    @Test
    void addGoodGivenTest() {
        DTO.ProductRequestDto product = randGoodAndPrice(new Random());

        int createdId = given()
                    .spec(basicRQAuth)
                    .body(product)
                    .when()
                    .post(Urls.ADD)
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("data.id", notNullValue())
                    .body("message", notNullValue())
                    .body("message", equalTo("success"))
                    .extract().path("data.id");

        given()
                .spec(basicRQ)
                .queryParam("size", 100)
                .queryParam("page", 0)
                .get(Urls.LIST)
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("goods.name", hasItem(product.name()))
                .body("goods.price", hasItem(product.price().floatValue()))
                .body("goods.id", hasItem(createdId));



    }

    @DisplayName("RequestSpecTest(4.1.2)")
    @Test
    void getRequestTest() {
        Response response = new GoodsApi()
                .getGoodsList(0, 10);
        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(200)
                .listIsEmpty("goods");
    }

    @DisplayName("addGoodsRequestTest(4.1.3)")
    @Test
    void addGoodRequestTest() {
        GoodsApi goodsApi = new GoodsApi();
        DTO.ProductRequestDto product = randGoodAndPrice(new Random());
        Response responsePost = goodsApi.createGood(product);

        BasicApiAssert.assertThat(responsePost)
                .statusCodeIsEquals(200)
                .fieldIsExist("data.id")
                .fieldIsExist("message")
                .fieldIsEquals("message", "success");


        Response responseGet = goodsApi.getGoodsList(0, 10);

        BasicApiAssert.assertThat(responseGet)
                .statusCodeIsEquals(200)
                .listContainsProduct("goods", product);
    }

}

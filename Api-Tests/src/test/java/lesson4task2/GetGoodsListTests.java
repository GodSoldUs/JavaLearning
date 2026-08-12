package lesson4task2;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import rest.assertions.BasicApiAssert;
import rest.endpoints.GoodsApi;


@DisplayName("[Get]/goods/list")
@Tag("Api-test")
public class GetGoodsListTests {

    @BeforeEach
    void start() {
        System.out.println("========================\n" + "Test method start");
    }

    @AfterEach
    void finish() {
        System.out.println("Test method end\n" + "========================");
    }

    GoodsApi goodsApi = new GoodsApi();

    @DisplayName("GET /goods/list HappyFlow")
    @Test
    void getList() {
        Response response = goodsApi.getGoodsList(0, 50);

        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(200)
                .contentTypeIsJson();
    }

    @DisplayName("GET /goods/list 200. Empty response")
    @Test
    void getListEmpty() {
        Response response = goodsApi.getGoodsList(100, 50);

        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(200)
                .contentTypeIsJson()
                .listIsEmpty("goods");
    }

    @DisplayName("GET /goods/list 400. Negative page")
    @Test
    void getListNegativePage() {
        Response response = goodsApi.getGoodsList(-1, 50);

        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(400);

    }


    @DisplayName("GET /goods/list 400. Negative size")
    @Test
    void getListNegativeSize() {
        Response response = goodsApi.getGoodsList(1, -50);

        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(400);

    }

}

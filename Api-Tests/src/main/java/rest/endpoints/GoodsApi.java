package rest.endpoints;

import io.qameta.allure.Step;
import io.restassured.response.Response;


import static rest.RestApiBuilder.getBuilder;
import static rest.RestApiBuilder.getBuilderWithoutAuth;
import static rest.endpoints.Urls.*;

public class GoodsApi {



    @Step("Создать товар '{createdProduct}'")
    public Response createGood(DTO.ProductRequestDto createdProduct) {
        return getBuilder().setContentJSON().getSpec()
                .body(createdProduct)
                .post(ADD);
    }
    @Step("Создать товар '{createdProduct}' без авторизации")
    public Response createGoodWithoutAuth(DTO.ProductRequestDto createdProduct) {
        return getBuilderWithoutAuth().setContentJSON().getSpec()
                .body(createdProduct)
                .post(ADD);
    }

    @Step("Создать товар не валидное тнло запроса ")
    public Response createGoodBadRequest(Object requestBody) {
        return getBuilder()
                .setContentJSON()
                .getSpec()
                .body(requestBody)
                .post(ADD);
    }


    @Step("Получить список товаров")
    public Response getGoodsList(int page, int size) {
        return getBuilder().getSpec()
                .queryParam("size", size)
                .queryParam("page", page)
                .get(LIST);
    }

    @Step("Получить товар по id = '{id}'")
    public Response getGoodFromId(int id) {
        return getBuilder()
                .getSpec()
                .pathParam("id", id)
                .get(ID);
    }

    @Step("Получить товар по id = '{id}'")
    public Response getGoodFromIdString(String id) {
        return getBuilder()
                .getSpec()
                .pathParam("id", id)
                .get(ID);
    }

    @Step("Удалить товар по id = '{id}'")
    public Response deleteGood(int id) {
        return getBuilder()
                .getSpec()
                .pathParam("id", id)
                .delete(ID);
    }

    @Step("Удалить товар по id = '{id}'")
    public Response deleteGoodString(String id) {
        return getBuilder()
                .getSpec()
                .pathParam("id", id)
                .delete(ID);
    }

    @Step("Удалить товар по id = '{id}' без авторизации")
    public Response deleteGoodWithoutAuth(int id) {
        return getBuilderWithoutAuth()
                .getSpec()
                .pathParam("id", id)
                .delete(ID);
    }

    @Step("Обновить товар по id = '{id}'")
    public Response updateGood(int id, Object requestBody) {
        return getBuilder()
                .setContentJSON()
                .getSpec()
                .pathParam("id", id)
                .body(requestBody)
                .patch(ID);
    }

    @Step("Обновить товар по id = '{id}' без аввторизации")
    public Response updateGoodWithoutAuth(int id, Object requestBody) {
        return getBuilderWithoutAuth()
                .setContentJSON()
                .getSpec()
                .pathParam("id", id)
                .body(requestBody)
                .patch(ID);
    }

    @Step("Обновить товар по id = '{id}'")
    public Response updateGoodStringId(String id, Object requestBody) {
        return getBuilder()
                .setContentJSON()
                .getSpec()
                .pathParam("id", id)
                .body(requestBody)
                .patch(ID);
    }

    @Step("Обновить товар по id = '{id}' без тела запроса")
    public Response updateGoodWithoutBody(int id) {
        return getBuilder()
                .setContentJSON()
                .getSpec()
                .pathParam("id", id)
                .patch(ID);
    }






}

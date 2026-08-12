package rest.endpoints;

import io.restassured.response.Response;


import static rest.RestApiBuilder.getBuilder;
import static rest.RestApiBuilder.getBuilderWithoutAuth;
import static rest.endpoints.Urls.*;

public class GoodsApi {




    public Response createGood(DTO.ProductRequestDto createdProduct) {
        return getBuilder().setContentJSON().getSpec()
                .body(createdProduct)
                .post(ADD);
    }

    public Response createGoodWithoutAuth(DTO.ProductRequestDto createdProduct) {
        return getBuilderWithoutAuth().setContentJSON().getSpec()
                .body(createdProduct)
                .post(ADD);
    }

    public Response createGoodBadRequest(Object requestBody) {
        return getBuilder()
                .setContentJSON()
                .getSpec()
                .body(requestBody)
                .post(ADD);
    }



    public Response getGoodsList(int page, int size) {
        return getBuilder().getSpec()
                .queryParam("size", size)
                .queryParam("page", page)
                .get(LIST);
    }


    public Response getGoodFromId(int id) {
        return getBuilder()
                .getSpec()
                .pathParam("id", id)
                .get(ID);
    }

    public Response getGoodFromIdString(String id) {
        return getBuilder()
                .getSpec()
                .pathParam("id", id)
                .get(ID);
    }


    public Response deleteGood(int id) {
        return getBuilder()
                .getSpec()
                .pathParam("id", id)
                .delete(ID);
    }

    public Response deleteGoodString(String id) {
        return getBuilder()
                .getSpec()
                .pathParam("id", id)
                .delete(ID);
    }

    public Response deleteGoodWithoutAuth(int id) {
        return getBuilderWithoutAuth()
                .getSpec()
                .pathParam("id", id)
                .delete(ID);
    }

    public Response updateGood(int id, Object requestBody) {
        return getBuilder()
                .setContentJSON()
                .getSpec()
                .pathParam("id", id)
                .body(requestBody)
                .patch(ID);
    }

    public Response updateGoodWithoutAuth(int id, Object requestBody) {
        return getBuilderWithoutAuth()
                .setContentJSON()
                .getSpec()
                .pathParam("id", id)
                .body(requestBody)
                .patch(ID);
    }

    public Response updateGoodStringId(String id, Object requestBody) {
        return getBuilder()
                .setContentJSON()
                .getSpec()
                .pathParam("id", id)
                .body(requestBody)
                .patch(ID);
    }

    public Response updateGoodWithoutBody(int id) {
        return getBuilder()
                .setContentJSON()
                .getSpec()
                .pathParam("id", id)
                .patch(ID);
    }






}

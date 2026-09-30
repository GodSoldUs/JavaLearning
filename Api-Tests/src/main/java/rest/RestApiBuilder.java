package rest;

import config.ConfigProvider;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static rest.endpoints.Urls.GOODS;


public class RestApiBuilder {
    RequestSpecification spec;

    /*private static final String
            BASIC_URL = "http://localhost:8080",
            LOGIN = "admin",
            PASSWORD = "secret123";*/

    public RestApiBuilder() {
        spec = given().baseUri(ConfigProvider.CONFIG.Url())
                .filter(new AllureRestAssured())
                .basePath(GOODS)
                .log().all()
                .relaxedHTTPSValidation();
    }

    public RestApiBuilder addAuth(String login, String password) {
        spec = spec.auth().basic(login, password);
        return this;
    }

    public RequestSpecification getSpec() {
        return spec;
    }

    public RestApiBuilder setContentJSON() {
        spec= spec.contentType(ContentType.JSON);
        return this;
    }

    public static RestApiBuilder getBuilder() {
        return new RestApiBuilder().addAuth(ConfigProvider.CONFIG.login(), ConfigProvider.CONFIG.password());
    }

    public static RestApiBuilder getBuilderWithoutAuth() {
        return new RestApiBuilder();
    }

}

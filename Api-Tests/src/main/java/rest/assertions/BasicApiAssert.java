package rest.assertions;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;


import java.util.List;

public class BasicApiAssert extends AbstractAssert<BasicApiAssert, Response> {
    public BasicApiAssert(Response actual) {
        super(actual, BasicApiAssert.class);
        actual.prettyPrint();
    }

    public static BasicApiAssert assertThat(Response actual) {
        return new BasicApiAssert(actual);
    }

    @Step("Код ответа равен '{code}'")
    public BasicApiAssert statusCodeIsEquals(int code) {
        Assertions.assertThat(actual.statusCode())
                .as("Status code must be %d".formatted(code))
                .isEqualTo(code);
        return this;
    }

    @Step("Поле '{path}' существует")
    public BasicApiAssert fieldIsExist(String path) {
        Assertions.assertThat(actual.jsonPath().getString(path))
                .as("Field with path %s must be exist!".formatted(path))
                .isNotNull();
        return this;
    }

    @Step("Поле '{path}' равно '{value}' ")
    public BasicApiAssert fieldIsEquals(String path, String value) {
        Assertions.assertThat(actual.jsonPath().getString(path))
                .as("Field with path %s must be equal '%s'!".formatted(path, value))
                .isEqualToIgnoringCase(value);
        return this;
    }


    @Step("header '{header}' равно '{value}' ")
    public BasicApiAssert headerIsEqual(String header, String value) {
        Assertions.assertThat(actual.getHeader(header))
                .as("Header '%s' must be equal '%s' ".formatted(header,value))
                .isEqualToIgnoringCase(value);
        return this;

    }

    @Step("List with path '{path}' must be empty")
    public BasicApiAssert listIsEmpty(String path) {
        Assertions.assertThat(actual.jsonPath().getList(path,String.class))
                .as("List with path %s must be empty")
                .isEmpty();

        return this;
    }

    @Step("Good list must with path '{path}' contains created product '{expectedProduct}'")
    public BasicApiAssert listContainsProduct(String path, DTO.ProductRequestDto expectedProduct) {
        List<DTO.ProductResponseDto> actualList =
                actual.jsonPath().getList(path, DTO.ProductResponseDto.class);
        Assertions.assertThat(actualList)
                .as("Good list must contains created product")
                .isNotEmpty()
                .anySatisfy(actual -> {
                    Assertions.assertThat(actual.name()).isEqualTo(expectedProduct.name());
                    Assertions.assertThat(actual.price()).isEqualTo(expectedProduct.price());
                })
                ;
        return this;
    }

    @Step("Body must be empty")
    public BasicApiAssert emptyResponse() {
        Assertions.assertThat(actual.asString().trim())
                .as("Body must be empty")
                .isEqualTo("{}");
        return this;
    }


    @Step("Content-Type must be application/json")
    public BasicApiAssert contentTypeIsJson() {
        Assertions.assertThat(actual.contentType())
                .as("Content-Type must be application/json")
                .containsIgnoringCase("application/json");
        return this;
    }




}

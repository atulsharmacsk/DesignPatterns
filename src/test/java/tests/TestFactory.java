package tests;

import factory.service.Service;
import factory.service.ServiceFactory;
import factory.service.Services;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Map;

public class TestFactory {

    private static RequestSpecification requestSpecification;
    private static Response response;

    static {
        requestSpecification = RestAssured.given()
                .contentType(ContentType.JSON);
    }

    @Test
    public void testSingleProduct() {
        Service service = ServiceFactory.getService(Services.PRODUCT);
        String endPoint = service.buildEndPoint("a product");
        requestSpecification.baseUri(service.getBaseURL());
        Map<String,String> pathParams= Map.of("id","1");

        response = requestSpecification
                .pathParams(pathParams)
                .get(endPoint);

        System.out.println(response.asString());
        Assertions.assertThat(response.statusCode())
                .isEqualTo(200);

    }

    @Test
    public void testSingleBrand() {
        Service service = ServiceFactory.getService(Services.BRAND);
        String endPoint = service.buildEndPoint("a brand");
        requestSpecification.baseUri(service.getBaseURL());
        Map<String,String> pathParams= Map.of("id","1");

        response = requestSpecification
                .pathParams(pathParams)
                .get(endPoint);

        System.out.println(response.asString());
        Assertions.assertThat(response.statusCode())
                .isEqualTo(200);

    }
}

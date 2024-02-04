package tests;

import factory.service.*;
import factory.service.SOM.ProductService;
import factory.service.SOM.RequestConfig;
import factory.service.SOM.ResponseGenerator;
import factory.service.SOM.RequestUtil;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

public class TestSOMs {

    @Test
    public void testProducts() {
        RequestConfig config = RequestConfig.builder()
                .baseURL(RequestUtil.getBaseURL(Services.PRODUCT))
                .endpoint(RequestUtil.getEndPoint(Services.PRODUCT, ProductService.EndPoints.PRODUCTS))
                .method(Method.GET)
                .build();

        Response response = ResponseGenerator.builder()
                .requestConfig(config)
                .build()
                .getResponse();

        System.out.println(response.asString());
    }

    @Test
    public void testSingleProduct() {
        RequestConfig config = RequestConfig.builder()
                .baseURL(RequestUtil.getBaseURL(Services.PRODUCT))
                .endpoint(RequestUtil.getEndPoint(Services.PRODUCT, ProductService.EndPoints.PRODUCT))
                .pathParams(Map.of("id","1"))
                .method(Method.GET)
                .build();

        Response response = ResponseGenerator.builder()
                .requestConfig(config)
                .build()
                .getResponse();

        System.out.println(response.asString());
    }
}

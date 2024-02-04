package factory.service.SOM;

import factory.service.Service;
import factory.service.Services;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

public class ProductService implements Service {

    private final Map<String, String> endPoints = Map.of(
            EndPoints.PRODUCTS, "/products",
            EndPoints.PRODUCT, "/products/{id}"
    );
    @Override
    public String buildEndPoint(String ep) {
        return endPoints.get(ep);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class EndPoints {
        public static final String PRODUCTS = "products";
        public static final String PRODUCT = "a product";
    }

    public static Response getProducts(){
        RequestConfig config = RequestConfig.builder()
                .baseURL(RequestUtil.getBaseURL(Services.PRODUCT))
                .endpoint(RequestUtil.getEndPoint(Services.PRODUCT, ProductService.EndPoints.PRODUCTS))
                .method(Method.GET)
                .build();

        return ResponseGenerator.builder()
                .requestConfig(config)
                .build()
                .getResponse();
    }

    public static Response getSingleProduct(Map<String, ?> pathParams){
        RequestConfig config = RequestConfig.builder()
                .baseURL(RequestUtil.getBaseURL(Services.PRODUCT))
                .endpoint(RequestUtil.getEndPoint(Services.PRODUCT, EndPoints.PRODUCT))
                .pathParams(pathParams)
                .method(Method.GET)
                .build();

        return ResponseGenerator.builder()
                .requestConfig(config)
                .build()
                .getResponse();
    }
}

package factory.service.SOM;

import factory.service.Service;
import factory.service.Services;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

public class BrandService implements Service {

    private final Map<String, String> endPoints = Map.of(
            EndPoints.BRANDS, "/brands",
            EndPoints.BRAND, "/brands/{id}"
    );

    @Override
    public String buildEndPoint(String ep) {
        return endPoints.get(ep);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class EndPoints {
        public static final String BRANDS = "brands";
        public static final String BRAND = "a brand";
    }

    public static Response getBrands(){
        RequestConfig config = RequestConfig.builder()
                .baseURL(RequestUtil.getBaseURL(Services.BRAND))
                .endpoint(RequestUtil.getEndPoint(Services.BRAND, EndPoints.BRANDS))
                .method(Method.GET)
                .build();

        return ResponseGenerator.builder()
                .requestConfig(config)
                .build()
                .getResponse();
    }

    public static Response getSingleBrand(Map<String, ?> pathParams){
        RequestConfig config = RequestConfig.builder()
                .baseURL(RequestUtil.getBaseURL(Services.BRAND))
                .endpoint(RequestUtil.getEndPoint(Services.BRAND, EndPoints.BRAND))
                .pathParams(pathParams)
                .method(Method.GET)
                .build();

        return ResponseGenerator.builder()
                .requestConfig(config)
                .build()
                .getResponse();
    }
}

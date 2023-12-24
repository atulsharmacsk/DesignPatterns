package factory.service.subclsses;

import factory.service.Service;
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
}

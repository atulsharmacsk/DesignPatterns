package factory.service.subclsses;

import factory.service.Service;

import java.util.Map;

public class ProductService implements Service {

    private final Map<String, String> endPoints= Map.of(
            "products", "/products",
            "a product","/products/{id}"
    );

    @Override
    public String buildEndPoint(String ep) {
        return endPoints.get(ep);
    }
}

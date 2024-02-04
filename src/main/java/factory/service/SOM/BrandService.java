package factory.service.SOM;

import factory.service.Service;
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
}

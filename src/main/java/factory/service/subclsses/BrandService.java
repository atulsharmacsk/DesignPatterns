package factory.service.subclsses;

import factory.service.Service;

import java.util.Map;

public class BrandService implements Service {

    private final Map<String, String> endPoints= Map.of(
            "brands", "/brands",
            "a brand","/brands/{id}"
    );

    @Override
    public String buildEndPoint(String ep) {
        return endPoints.get(ep);
    }
}

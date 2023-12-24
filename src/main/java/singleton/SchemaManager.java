package singleton;

import Utils.FileUtils;
import factory.service.subclsses.BrandService;
import factory.service.subclsses.ProductService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SchemaManager {
    private static SchemaManager schemaManager;
    private final Map<String, String> schemasMap = Map.of(
            BrandService.EndPoints.BRANDS, "getBrands.json",
            ProductService.EndPoints.PRODUCTS, "getProducts.json"
    );

    public static SchemaManager getSchemaManager() {
        return schemaManager == null ? new SchemaManager() : schemaManager;
    }

    public String getSchema(String endPoint) {
        return FileUtils.readFile("Schema/" + schemasMap.get(endPoint));
    }


}

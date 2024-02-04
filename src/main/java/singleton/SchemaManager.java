package singleton;

import Utils.FileUtils;
import factory.service.SOM.BrandService;
import factory.service.SOM.ProductService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SchemaManager {
    private static SchemaManager schemaManager;

    private final Map<String, String> schemasMap = Map.of(
            BrandService.EndPoints.BRANDS, "getBrands.json",
            ProductService.EndPoints.PRODUCTS, "getProducts.json"
    );

    @SneakyThrows
    public static SchemaManager getSchemaManager() {
        if (schemaManager == null) {
            schemaManager = new SchemaManager();
        }
        return schemaManager;
    }

    public String getSchema(String endPoint) {
        return FileUtils.readFile("Schema/" + schemasMap.get(endPoint));
    }


}

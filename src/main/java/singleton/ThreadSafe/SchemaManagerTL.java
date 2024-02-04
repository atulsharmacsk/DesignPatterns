package singleton.ThreadSafe;

import Utils.FileUtils;
import factory.service.SOM.BrandService;
import factory.service.SOM.ProductService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SchemaManagerTL {

    // thread safe but one instance per thread
    private static ThreadLocal<SchemaManagerTL> schemaManager;

    private final Map<String, String> schemasMap = Map.of(
            BrandService.EndPoints.BRANDS, "getBrands.json",
            ProductService.EndPoints.PRODUCTS, "getProducts.json"
    );

    @SneakyThrows
    public static SchemaManagerTL getSchemaManager() {
        if (schemaManager.get() == null) {
            schemaManager.set(new SchemaManagerTL());
        }
        return schemaManager.get();
    }

    public String getSchema(String endPoint) {
        return FileUtils.readFile("Schema/" + schemasMap.get(endPoint));
    }
}

package singleton.ThreadSafe;

import Utils.FileUtils;
import factory.service.SOM.BrandService;
import factory.service.SOM.ProductService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SchemaManagerSynchMethod {

    private static SchemaManagerSynchMethod schemaManager;

    private final Map<String, String> schemasMap = Map.of(
            BrandService.EndPoints.BRANDS, "getBrands.json",
            ProductService.EndPoints.PRODUCTS, "getProducts.json"
    );

    // thread safe & only one instance per multiple threads but slow
    // as it waits till previous thread completes
    @SneakyThrows
    public static synchronized SchemaManagerSynchMethod getSchemaManager() {
        if (schemaManager == null) {
            schemaManager = new SchemaManagerSynchMethod();
        }
        return schemaManager;
    }

    public String getSchema(String endPoint) {
        return FileUtils.readFile("Schema/" + schemasMap.get(endPoint));
    }


}

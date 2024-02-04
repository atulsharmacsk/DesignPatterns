package singleton.ThreadSafe;

import Utils.FileUtils;
import factory.service.SOM.BrandService;
import factory.service.SOM.ProductService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SchemaManagerStaticInner {

    private final Map<String, String> schemasMap = Map.of(
            BrandService.EndPoints.BRANDS, "getBrands.json",
            ProductService.EndPoints.PRODUCTS, "getProducts.json"
    );

    private static class Inner {
        private static final SchemaManagerStaticInner schemaManager = new SchemaManagerStaticInner();
    }

    //inner static classes are lazily loaded by JVM, upon first invocation
    @SneakyThrows
    public static SchemaManagerStaticInner getSchemaManager() {
        return Inner.schemaManager;
    }

    public String getSchema(String endPoint) {
        return FileUtils.readFile("Schema/" + schemasMap.get(endPoint));
    }


}

package singleton.ThreadSafe;

import Utils.FileUtils;
import factory.service.subclsses.BrandService;
import factory.service.subclsses.ProductService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SchemaManagerDoubleChecked {

    private static volatile SchemaManagerDoubleChecked schemaManager;

    private final Map<String, String> schemasMap = Map.of(
            BrandService.EndPoints.BRANDS, "getBrands.json",
            ProductService.EndPoints.PRODUCTS, "getProducts.json"
    );

    //only locking critical section of code, the code which
    //creates instance of Singleton class.
    @SneakyThrows
    public static SchemaManagerDoubleChecked getSchemaManager() {
        if (schemaManager == null) {
            synchronized (SchemaManagerDoubleChecked.class) {
                if (schemaManager == null)
                    schemaManager = new SchemaManagerDoubleChecked();
            }
        }
        return schemaManager;
    }

    // the volatile field is only accessed once
    // (due to "return temp;" instead of "return schemaManager;"), which can improve the method's overall performance
    @SneakyThrows
    public static SchemaManagerDoubleChecked getSchemaManager2() {
        SchemaManagerDoubleChecked temp = schemaManager;
        if (temp == null) {
            synchronized (SchemaManagerDoubleChecked.class) {
                if (temp == null)
                    schemaManager = temp = new SchemaManagerDoubleChecked();
            }
        }
        return temp;
    }

    public String getSchema(String endPoint) {
        return FileUtils.readFile("Schema/" + schemasMap.get(endPoint));
    }


}

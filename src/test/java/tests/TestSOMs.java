package tests;

import factory.service.SOM.BrandService;
import factory.service.SOM.ProductService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

public class TestSOMs {
    @Test
    public void testProducts() {
        Response response = ProductService.getProducts();
        System.out.println(response.asString());
    }

    @Test
    public void testSingleProduct() {
        Response response = ProductService
                .getSingleProduct(Map.of("id","1"));
        System.out.println(response.asString());
    }

    @Test
    public void testBrands() {
        Response response = BrandService.getBrands();
        System.out.println(response.asString());
    }

    @Test
    public void testSingleBrand() {
        Response response = BrandService
                .getSingleBrand(Map.of("id","1"));
        System.out.println(response.asString());
    }
}

package tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import factory.service.Service;
import factory.service.ServiceFactory;
import factory.service.Services;
import factory.service.subclsses.BrandService;
import factory.service.subclsses.ProductService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.module.jsv.JsonSchemaValidatorSettings;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import singleton.SchemaManager;

import java.io.File;

public class TestSingleton {

    private static RequestSpecification requestSpecification;
    private static Response response;
    ObjectMapper mapper = new ObjectMapper();

    static {
        requestSpecification = RestAssured.given()
                .contentType(ContentType.JSON);
    }

    @SneakyThrows
    @Test
    public void testProductsSchema() {
        Service service = ServiceFactory.getService(Services.PRODUCT);
        String endPoint = service.buildEndPoint(ProductService.EndPoints.PRODUCTS);
        requestSpecification.baseUri(service.getBaseURL());

        requestSpecification
                .get(endPoint)
                .then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(
                        new File(System.getProperty("user.dir")
                                + "/src/main/resources/Schema/getProducts.json")));
    }

    @SneakyThrows
    @Test
    public void testProductsSchemaDeeply() {
        Service service = ServiceFactory.getService(Services.PRODUCT);
        String endPoint = service.buildEndPoint(ProductService.EndPoints.PRODUCTS);
        requestSpecification.baseUri(service.getBaseURL());

        Response response = requestSpecification
                .get(endPoint);
        String schema = SchemaManager.getSchemaManager()
                .getSchema(ProductService.EndPoints.PRODUCTS);

        JsonNode expectedSchema = mapper.readTree(schema);
        JsonNode actualSchema = mapper.readTree(response.asString());

        ProcessingReport report = JsonSchemaValidatorSettings
                .settings().jsonSchemaFactory()
                .getValidator()
                .validate(expectedSchema, actualSchema, true);

        Assertions.assertThat(report.isSuccess())
                .withFailMessage("schema mismatches at %s", report)
                .isTrue();
    }

    @SneakyThrows
    @Test
    public void testBrandsSchema() {
        Service service = ServiceFactory.getService(Services.BRAND);
        String endPoint = service.buildEndPoint(BrandService.EndPoints.BRANDS);
        requestSpecification.baseUri(service.getBaseURL());

        response = requestSpecification
                .get(endPoint);

        String schema = SchemaManager.getSchemaManager()
                .getSchema(BrandService.EndPoints.BRANDS);

        ProcessingReport report = JsonSchemaValidatorSettings
                .settings().jsonSchemaFactory()
                .getValidator()
                .validate(mapper.readTree(schema), mapper.readTree(response.asString()), true);

        Assertions.assertThat(report.isSuccess())
                .withFailMessage("schema mismatches at %s", report)
                .isTrue();


    }
}

package tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import factory.service.Service;
import factory.service.ServiceFactory;
import factory.service.Services;
import factory.service.SOM.BrandService;
import factory.service.SOM.ProductService;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.module.jsv.JsonSchemaValidatorSettings;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import singleton.ThreadSafe.SchemaManagerStaticInner;

public class TestSingletonThread {

    @SneakyThrows
    @Test
    public void testProductsSchemaDeeply() {
        Service service = ServiceFactory.getService(Services.PRODUCT);
        String endPoint = service.buildEndPoint(ProductService.EndPoints.PRODUCTS);

        Response response = RestAssured.given()
                .baseUri(service.getBaseURL())
                .get(endPoint);
        String schema = SchemaManagerStaticInner.getSchemaManager()
                .getSchema(ProductService.EndPoints.PRODUCTS);

        ObjectMapper mapper = new ObjectMapper();
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
        ThreadLocal<String> endPoint = ThreadLocal.withInitial(() -> null);
        endPoint.set(service.buildEndPoint(BrandService.EndPoints.BRANDS));

        Response response = RestAssured.given()
                .baseUri(service.getBaseURL())
                .get(endPoint.get());

        String schema = SchemaManagerStaticInner.getSchemaManager()
                .getSchema(BrandService.EndPoints.BRANDS);

        ObjectMapper mapper = new ObjectMapper();

        ProcessingReport report = JsonSchemaValidatorSettings
                .settings().jsonSchemaFactory()
                .getValidator()
                .validate(mapper.readTree(schema), mapper.readTree(response.asString()), true);

        Assertions.assertThat(report.isSuccess())
                .withFailMessage("schema mismatches at %s", report)
                .isTrue();
    }

    @SneakyThrows
    @Test
    public void testBrandsSchema2() {
        Service service = ServiceFactory.getService(Services.BRAND);
        String endPoint = service.buildEndPoint(BrandService.EndPoints.BRANDS);

        Response response = RestAssured.given()
                .baseUri(service.getBaseURL())
                .config(RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig()))
                .get(endPoint);

        String schema = SchemaManagerStaticInner.getSchemaManager()
                .getSchema(BrandService.EndPoints.BRANDS);

        ObjectMapper mapper = new ObjectMapper();

        ProcessingReport report = JsonSchemaValidatorSettings
                .settings().jsonSchemaFactory()
                .getValidator()
                .validate(mapper.readTree(schema), mapper.readTree(response.asString()), true);

        Assertions.assertThat(report.isSuccess())
                .withFailMessage("schema mismatches at %s", report)
                .isTrue();
    }

    @SneakyThrows
    @Test
    public void testBrandsSchema3() {
        Service service = ServiceFactory.getService(Services.BRAND);
        String endPoint = service.buildEndPoint(BrandService.EndPoints.BRANDS);

        Response response = RestAssured.given()
                .baseUri(service.getBaseURL())
                .get(endPoint);

        String schema = SchemaManagerStaticInner.getSchemaManager()
                .getSchema(BrandService.EndPoints.BRANDS);

        ObjectMapper mapper = new ObjectMapper();
        ProcessingReport report = JsonSchemaValidatorSettings
                .settings().jsonSchemaFactory()
                .getValidator()
                .validate(mapper.readTree(schema), mapper.readTree(response.asString()), true);

        Assertions.assertThat(report.isSuccess())
                .withFailMessage("schema mismatches at %s", report)
                .isTrue();
    }

    @SneakyThrows
    @Test
    public void testBrandsSchema4() {
        Service service = ServiceFactory.getService(Services.BRAND);
        String endPoint = service.buildEndPoint(BrandService.EndPoints.BRANDS);

        Response response = RestAssured.given()
                .baseUri(service.getBaseURL())
                .get(endPoint);

        String schema = SchemaManagerStaticInner.getSchemaManager()
                .getSchema(BrandService.EndPoints.BRANDS);

        ObjectMapper mapper = new ObjectMapper();
        ProcessingReport report = JsonSchemaValidatorSettings
                .settings().jsonSchemaFactory()
                .getValidator()
                .validate(mapper.readTree(schema), mapper.readTree(response.asString()), true);

        Assertions.assertThat(report.isSuccess())
                .withFailMessage("schema mismatches at %s", report)
                .isTrue();
    }

    @SneakyThrows
    @Test
    public void testBrandsSchema5() {
        Service service = ServiceFactory.getService(Services.PRODUCT);
        String endPoint = service.buildEndPoint(ProductService.EndPoints.PRODUCTS);

        Response response = RestAssured.given()
                .baseUri(service.getBaseURL())
                .get(endPoint);
        String schema = SchemaManagerStaticInner.getSchemaManager()
                .getSchema(ProductService.EndPoints.PRODUCTS);

        ObjectMapper mapper = new ObjectMapper();
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
}

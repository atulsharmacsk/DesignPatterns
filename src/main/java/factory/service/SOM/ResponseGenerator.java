package factory.service.SOM;

import factory.service.SOM.RequestConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Builder
@Getter
public class ResponseGenerator {
    private final RequestConfig requestConfig;

    private RequestSpecification requestSpecification() {
        RestAssuredConfig config = new RestAssuredConfig().httpClient(
                HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout", 10000)
                        .setParam("http.connection.timeout", 10000)
        )
                .sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());


        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setContentType(ContentType.ANY)
                .setBaseUri(requestConfig.getBaseURL())
                .setConfig(config);

        if(!Objects.isNull(requestConfig.getBody()))
            builder.setBody(requestConfig.getBody());

        if(!Objects.isNull(requestConfig.getQueryParams()))
            builder.addQueryParams(requestConfig.getQueryParams());

        if(!Objects.isNull(requestConfig.getPathParams()))
            builder.addPathParams(requestConfig.getPathParams());

        return RestAssured.given().spec(builder.build());
    }

    public Response getResponse() {
        switch (requestConfig.getMethod()) {
            case GET:
                    return requestSpecification().get(requestConfig.getEndpoint());
            case POST:
                    return requestSpecification().post(requestConfig.getEndpoint());
            default:
                throw new RuntimeException("Method is wrongly entered");
        }
    }
}


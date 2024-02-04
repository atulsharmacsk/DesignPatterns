package factory.service.SOM;

import io.restassured.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Builder
@Getter
public class RequestConfig {
    private final Method method;
    private final String baseURL;
    private final String endpoint;
    private final Map<String, ?> pathParams;
    private final Map<String, ?> queryParams;
    private final Object body;

}

package factory.service;

public interface Service {
    String buildEndPoint(String ep);

    default String getBaseURL(){
        return "http://localhost:3000";
    }
}

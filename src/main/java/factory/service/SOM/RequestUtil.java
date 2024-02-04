package factory.service.SOM;

import factory.service.ServiceFactory;
import factory.service.Services;

public class RequestUtil {
    public static String getBaseURL(Services services){
        return ServiceFactory.getService(services).getBaseURL();
    }

    public static String getEndPoint(Services services, String readableEndPoint){
        return ServiceFactory.getService(services).buildEndPoint(readableEndPoint);
    }
}

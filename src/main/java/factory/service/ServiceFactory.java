package factory.service;

import factory.service.SOM.BrandService;
import factory.service.SOM.ProductService;

public class ServiceFactory {
    public static Service getService(Services service) {
     switch (service){
         case PRODUCT:
             return new ProductService();
         case BRAND:
             return new BrandService();
         default:
             throw new AssertionError("Service not found "+ service);
     }
    }
}

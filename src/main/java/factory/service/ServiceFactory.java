package factory.service;

import factory.service.subclsses.BrandService;
import factory.service.subclsses.ProductService;

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

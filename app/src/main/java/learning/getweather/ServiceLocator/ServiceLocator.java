package learning.getweather.ServiceLocator;

import learning.getweather.ServiceLocator.Services.DataService;

public class ServiceLocator {
    private static final ServiceFactory serviceFactory = new ServiceFactory();

    public static DataService getService(final String serviceName) {
        return serviceFactory.getService(serviceName);
    }
}

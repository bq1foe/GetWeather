package learning.getweather.serviceLocator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import learning.getweather.serviceLocator.Services.DataService;
import learning.getweather.serviceLocator.Services.SharedPreferencesService;

public class ServiceFactory {
    private final Set<Class> availableServices = new HashSet<>();
    private final Map<Class, DataService> cachedServices = new HashMap<>();

    public ServiceFactory() {
        availableServices.add(SharedPreferencesService.class);
    }

    public DataService getService(Class className) {
        return cachedServices.containsKey(className) ? cachedServices.get(className) : createDataService(className);
    }

    private DataService createDataService(Class className) {
        if (!availableServices.contains(className)) {
            return null;
        }
        DataService dataService = null;
        try {
            dataService = (DataService) className.newInstance();
            cachedServices.put(className, dataService);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return dataService;
    }
}

package learning.getweather.ServiceLocator;

import java.util.HashMap;
import java.util.Map;

import learning.getweather.ServiceLocator.Services.DataService;
import learning.getweather.ServiceLocator.Services.SharedPreferencesService;

public class ServiceFactory {
    private final Map<String, Class> availableServices = new HashMap<>();
    private final Map<String, DataService> cachedServices = new HashMap<>();

    public ServiceFactory() {
        availableServices.put(SharedPreferencesService.SERVICE_ID, SharedPreferencesService.class);
    }

    public DataService getService(final String name) {
        return cachedServices.containsKey(name) ? cachedServices.get(name) : createDataService(name);
    }

    private DataService createDataService(final String name) {
        if (!availableServices.containsKey(name)) {
            return null;
        }
        DataService dataService = null;
        try {
            dataService = (DataService) availableServices.get(name).newInstance();
            cachedServices.put(name, dataService);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return dataService;
    }
}

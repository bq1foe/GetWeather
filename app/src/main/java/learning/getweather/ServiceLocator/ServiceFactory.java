package learning.getweather.serviceLocator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import learning.getweather.rest.API;
import learning.getweather.serviceLocator.Services.SharedPreferencesService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {
    private static final String KHARKOV_URL = "http://api.openweathermap.org";

    private final Set<Class> availableServices = new HashSet<>();
    private final Map<Class, Object> cachedServices = new HashMap<>();

    public ServiceFactory() {
        availableServices.add(SharedPreferencesService.class);
        availableServices.add(API.class);
    }

    public Object getService(Class className) {
        return cachedServices.containsKey(className) ? cachedServices.get(className) : createDataService(className);
    }

    private Object createDataService(Class className) {
        if (!availableServices.contains(className)) {
            return null;
        } else if (className.equals(API.class)) {
            final Gson gson = new GsonBuilder()
//                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(KHARKOV_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            final API remoteService = retrofit.create(API.class);
            cachedServices.put(className, remoteService);
            return remoteService;
        }
        Object localDataService = null;
        try {
            localDataService = className.newInstance();
            cachedServices.put(className, localDataService);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return localDataService;
    }
}

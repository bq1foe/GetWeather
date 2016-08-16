package learning.getweather;

import android.app.Application;

import learning.getweather.ServiceLocator.ServiceFactory;
import learning.getweather.ServiceLocator.Services.DataService;

public class App extends Application {
    private static App instance;

    public App() {
        instance = this;
    }

    public static App getContext() {
        return instance;
    }

    private static final ServiceFactory serviceFactory = new ServiceFactory();

    public static DataService getService(final String serviceName) {
        return serviceFactory.getService(serviceName);
    }
}

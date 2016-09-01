package learning.getweather;

import android.app.Application;

import learning.getweather.serviceLocator.ServiceFactory;

public class App extends Application {
    private static final ServiceFactory serviceFactory = new ServiceFactory();
    private static App instance;

    public App() {
        instance = this;
    }

    public static App getContext() {
        return instance;
    }

    public static Object getService(Class className) {
        return serviceFactory.getService(className);
    }
}

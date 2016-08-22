package learning.getweather.serviceLocator.Services;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import learning.getweather.App;

public class SharedPreferencesService extends DataService<String> {
    private static final String PREV_TEMP = "previous_temperature";
    private static final String SADNESS = ":(";

    private final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());
    private final SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();


    @Override
    protected String initValue() {
        return sharedPreferences.getString(PREV_TEMP, SADNESS);
    }

    @Override
    protected void saveValue(String value) {
        sharedPreferencesEditor.putString(PREV_TEMP, value);
        sharedPreferencesEditor.apply();
    }
}

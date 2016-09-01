package learning.getweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import learning.getweather.openweather.entities.ResponseEntity;
import learning.getweather.rest.API;
import learning.getweather.serviceLocator.Services.LocalDataService;
import learning.getweather.serviceLocator.Services.SharedPreferencesService;
import learning.getweather.utils.TemperatureUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final LocalDataService sharedPrefService = (LocalDataService) App.getService(SharedPreferencesService.class);
    private static final API remoteService = (API) App.getService(API.class);
    private static final String CITY = "kharkov";
    private static final String API_KEY = "9bfc7fdacca9e381d7c6d6dcfcb2d635";
    private TextView weatherText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherText = (TextView) findViewById(R.id.tv1);

        try {
            setTemperature(getData());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getData() throws ExecutionException, InterruptedException {
        final Call<ResponseEntity> call = remoteService.getCurrentWeather(CITY, API_KEY);
        call.enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                setTemperature(TemperatureUtil.convertKelvinToCelsius(response.body().getMain().getTemp()));
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                // should I log it?
            }
        });
        return (String) sharedPrefService.getValue();
    }

    private void setTemperature(String value) {
        if (!weatherText.getText().equals(value)) {
            sharedPrefService.setValue(value);
            weatherText.setText(value);
        }
    }
}

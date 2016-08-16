package learning.getweather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import learning.getweather.ServiceLocator.Services.SharedPreferencesService;

public class MainActivity extends AppCompatActivity {

    private static final String KHARKOV_URL = "http://api.openweathermap.org/data/2.5/weather?q=kharkov&appid=9bfc7fdacca9e381d7c6d6dcfcb2d635";
    private static final double TEMP_DIFF = 273.15;
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
        new WeatherChecker().execute(KHARKOV_URL);
        return (String) App.getService(SharedPreferencesService.SERVICE_ID).getValue();
    }

    @NonNull
    private String conversionToCelsius(Double kelvinValue) {
        final double celsiusValue = kelvinValue - TEMP_DIFF;
        return String.valueOf(celsiusValue);
    }

    private void setTemperature(String value) {
        if (!weatherText.getText().equals(value)) {
            App.getService(SharedPreferencesService.SERVICE_ID).setValue(value);
            weatherText.setText(value);
        }
    }

    private class WeatherChecker extends AsyncTask<String, Double, Void> {
        private static final String TAG = "WeatherChecker";

        @Override
        protected Void doInBackground(String... params) {
            try {
                final URL url = new URL(params[0]);
                final HttpURLConnection request = (HttpURLConnection) url.openConnection();
//                Gson gson = new Gson();

                for (; ; ) {
                    request.connect();
//                    gson.fromJson(new InputStreamReader((InputStream) request.getContent()));
                    final JsonReader jsonReader = new JsonReader(new InputStreamReader((InputStream) request.getContent()));
                    jsonReader.beginObject();
                    Thread.sleep(60000);
                    for (; ; ) {
                        if (jsonReader.peek() != JsonToken.NAME || !jsonReader.nextName().equals("main")) {
                            jsonReader.skipValue();
                            continue;
                        }
                        jsonReader.beginObject();
                        jsonReader.nextName();
                        break;
                    }
                    publishProgress(jsonReader.nextDouble());
                    request.disconnect();
                }
            } catch (Exception e) {
                Log.e(TAG, "WeatherChecker failed", e);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Double... values) {
            super.onProgressUpdate(values);
            if (values[0] != null) {
                setTemperature(conversionToCelsius(values[0]));
            }
        }
    }
}

package learning.getweather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.JsonToken;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static final String KHARKOV_URL = "http://api.openweathermap.org/data/2.5/weather?q=kharkov&appid=9bfc7fdacca9e381d7c6d6dcfcb2d635";
    private static final double TEMP_DIFF = 273.15;

    private TextView weatherText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherText = (TextView) findViewById(R.id.tv1);

        assert weatherText != null;
        try {
            weatherText.setText(getData());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getData() throws ExecutionException, InterruptedException {
        AsyncTask<String, Void, Double> weatherChecker = new WeatherChecker().execute(KHARKOV_URL);
        return "100"; //// TODO: 7/28/2016 implement previous temp pick
    }

    private String conversionToCelsius(Double kelvinValue){
        double celsiusValue = kelvinValue - TEMP_DIFF;
        return String.valueOf(celsiusValue);
    }

    private void setTemperature(String value){
        weatherText.setText(value);
    }

    private class WeatherChecker extends AsyncTask<String, Void, Double > {

        @Override
        protected Double doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.connect();

                JsonReader jsonReader = new JsonReader(new InputStreamReader((InputStream) request.getContent()));
                jsonReader.beginObject();
                Thread.sleep(20000);
                for(;;){
                    if(jsonReader.peek() != JsonToken.NAME || !jsonReader.nextName().equals("main")){
                        jsonReader.skipValue();
                        continue;
                    }
                    jsonReader.beginObject();
                    jsonReader.nextName();
                    break;
                }
                return jsonReader.nextDouble();
            } catch (Exception e){
                //// TODO: 7/28/2016 implemenet
                return null;
            }
        }

        @Override
        protected void onPostExecute(Double result) {
            super.onPostExecute(result);
            if(result != null) {
                setTemperature(conversionToCelsius(result));
            }
        }
    }
}

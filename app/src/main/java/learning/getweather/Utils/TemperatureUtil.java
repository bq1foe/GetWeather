package learning.getweather.utils;

public class TemperatureUtil {
    private static final double TEMP_DIFF = 273.15;

    public static String convertKelvinToCelsius(double kelvinValue){
        final double celsiusValue = kelvinValue - TEMP_DIFF;
        return String.valueOf(celsiusValue);
    }
}

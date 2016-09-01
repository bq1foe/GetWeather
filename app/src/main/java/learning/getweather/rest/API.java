package learning.getweather.rest;

import learning.getweather.openweather.entities.ResponseEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("/data/2.5/weather")
    Call<ResponseEntity> getCurrentWeather(@Query("q") String cityName, @Query("appid") String apiKey);

}

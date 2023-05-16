package org.example.weather;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.example.location.Location;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class WeatherServiceImpl implements WeatherService {

    private HttpClient httpClient;
    private final String API_KEY = "REPLACE WITH YOUR API KEY";

    public WeatherServiceImpl() {
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public WeatherData getWeatherData(Location location) {
        WeatherData weatherData = new WeatherData();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s&units=metric",
                         location.getLatitude(), location.getLongitude(),API_KEY)))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();
            JsonObject jsonObject = new Gson().fromJson(responseBody, JsonObject.class);

            double temp = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
            double pressure = jsonObject.getAsJsonObject("main").get("pressure").getAsDouble();
            double humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsDouble();
            double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
            double windDirection = jsonObject.getAsJsonObject("wind").get("deg").getAsDouble();

            weatherData.setTemperature(temp);
            weatherData.setPressure(pressure);
            weatherData.setHumidity(humidity);
            weatherData.setWindSpeed(windSpeed);
            weatherData.setWindDirection(windDirection);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return weatherData;
    }
}

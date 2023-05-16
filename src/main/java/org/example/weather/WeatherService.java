package org.example.weather;

import org.example.location.Location;

public interface WeatherService {
    WeatherData getWeatherData(Location location);
}

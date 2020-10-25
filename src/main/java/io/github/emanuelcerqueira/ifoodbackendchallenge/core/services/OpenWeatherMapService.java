package io.github.emanuelcerqueira.ifoodbackendchallenge.core.services;

public interface OpenWeatherMapService {
    Double celsiusTemperatureByPlaceName(String placeName);

    Double celsiusTemperatureByPlaceLatitudeAndLongitude(Double lat, Double lon);
}

package io.github.emanuelcerqueira.ifoodbackendchallenge.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.emanuelcerqueira.ifoodbackendchallenge.core.services.OpenWeatherMapService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class OpenWeatherMapServiceImpl implements OpenWeatherMapService {

    @Value("${open-weather-maps.api-base-url}")
    private String openWeatherMapApiBaseUrl;

    @Value("${open-weather-maps.api-key}")
    private String openWeatherMapApiKey;


    private final RestTemplate restTemplate;

    public OpenWeatherMapServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public Double placeTemperatureByUrlRequest(String url) {
        String responseJson = null;
        try {

            responseJson = restTemplate.getForObject(new URI(url), String.class);
            final ObjectNode node = new ObjectMapper().readValue(responseJson, ObjectNode.class);
            Double placeTemperature = node.get("main").get("temp").doubleValue();
            System.out.println("Place temperature: "+placeTemperature);
            return placeTemperature;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        catch (HttpClientErrorException.NotFound e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Double celsiusTemperatureByPlaceName(String placeName) {
        String searchParameter = null;
        try {
            searchParameter = "?q="+ URLEncoder.encode(placeName, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String urlRequest = getRequestUrlOpenStreetBySearchParameter(searchParameter);
        return placeTemperatureByUrlRequest(urlRequest);
    }

    @Override
    public Double celsiusTemperatureByPlaceLatitudeAndLongitude(Double lat, Double lon) {
        String searchParameter = "?lat="+lat+"&lon="+lon;
        String urlRequest = getRequestUrlOpenStreetBySearchParameter(searchParameter);
        return placeTemperatureByUrlRequest(urlRequest);
    }

    private String getRequestUrlOpenStreetBySearchParameter(String searchParameter) {

        StringBuilder urlRequest = new StringBuilder();
        urlRequest.append(openWeatherMapApiBaseUrl);
        urlRequest.append(searchParameter);
        urlRequest.append(celsiusInfixApiCall());
        urlRequest.append(openStreetMapApiCallKeySuffix());
        return urlRequest.toString();
    }

    private String openStreetMapApiCallKeySuffix() {
        return "&appid="+openWeatherMapApiKey;
    }

    private String celsiusInfixApiCall() {
        return "&units=metric";
    }

}

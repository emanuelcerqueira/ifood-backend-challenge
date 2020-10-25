package io.github.emanuelcerqueira.ifoodbackendchallenge.core.services;

import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.MusicGenre;
import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.Playlist;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PlaylistSuggestionService {

    private final OpenWeatherMapService openWeatherMapService;
    private final SpotifyService spotifyService;
    private final Set<GenreSuggestionRule> genreSuggestionRules;

    public PlaylistSuggestionService(OpenWeatherMapService openWeatherMapService, SpotifyService spotifyService, Set<GenreSuggestionRule> genreSuggestionRules) {
        this.openWeatherMapService = openWeatherMapService;
        this.spotifyService = spotifyService;
        this.genreSuggestionRules = genreSuggestionRules;
    }

    private Playlist playlistSuggestionByPlaceTemperature(Double temperature) {
        MusicGenre musicGenre = getSuitableMusicGenreByTemperature(temperature);
        Playlist playlist = spotifyService.findPlaylistByMusicGenre(musicGenre);
        return playlist;
    }

    private MusicGenre getSuitableMusicGenreByTemperature(Double temperature) {
        return genreSuggestionRules.stream()
               .filter(rule -> rule.isTemperatureSuitableToMusicGenre(temperature))
               .findFirst()
               .orElseThrow(() -> new RuntimeException()).getMusicGenre();
    }

    public Playlist playlistSuggestionByPlaceName(String placeName) {
        Double placeTemperature = openWeatherMapService.celsiusTemperatureByPlaceName(placeName);
        return playlistSuggestionByPlaceTemperature(placeTemperature);
    }

    public Playlist playlistSuggestionByPlaceLatitudeAndLongitude(Double lat, Double lon) {
        Double placeTemperature = openWeatherMapService.celsiusTemperatureByPlaceLatitudeAndLongitude(lat, lon);
        return playlistSuggestionByPlaceTemperature(placeTemperature);
    }

}

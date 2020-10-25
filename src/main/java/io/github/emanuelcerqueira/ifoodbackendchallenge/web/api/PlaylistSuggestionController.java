package io.github.emanuelcerqueira.ifoodbackendchallenge.web.api;

import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.Playlist;
import io.github.emanuelcerqueira.ifoodbackendchallenge.core.services.PlaylistSuggestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/playlist-suggestion")
@Validated
public class PlaylistSuggestionController {

    private final PlaylistSuggestionService playlistSuggestionService;

    public PlaylistSuggestionController(PlaylistSuggestionService playlistSuggestionService) {
        this.playlistSuggestionService = playlistSuggestionService;
    }

    @GetMapping(value = "by-name", params = {"name"})
    public ResponseEntity<Playlist> playlistSuggestionByPlaceName(
            @RequestParam("name") @NotEmpty String placeName) {

        Playlist playlist = playlistSuggestionService.playlistSuggestionByPlaceName(placeName);
        return ResponseEntity.ok().body(playlist);
    }

    @GetMapping(value = "by-lat-and-lon", params = {"lat", "lon"})
    public ResponseEntity<Playlist> playlistSuggestionByPlaceLatitudeAndLongitude(
            @RequestParam @Min(value = -90) @Max(90) Double lat,
            @Min(-180) @Max(180) @RequestParam Double lon) {
        Playlist playlist = playlistSuggestionService.playlistSuggestionByPlaceLatitudeAndLongitude(lat, lon);
        return ResponseEntity.ok().body(playlist);
    }
}

package io.github.emanuelcerqueira.ifoodbackendchallenge.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.MusicGenre;
import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.Playlist;
import io.github.emanuelcerqueira.ifoodbackendchallenge.core.services.SpotifyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class SpotifyServiceImpl implements SpotifyService {

    private final RestTemplate restTemplate;

    @Value("${spotify.api-key}")
    private String spotifyApiKey;

    @Value("${spotify.api-base-url}")
    private String spotifyApiBaseUrl;

    public SpotifyServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Playlist findPlaylistByMusicGenre(MusicGenre musicGenre) {
        Optional<Map<String, String>> optionalPlaylistNameAndTracksRequestUrl = getFirstPlaylistNameAndTracksRequestUrlByMusicGenre(musicGenre);

        if (optionalPlaylistNameAndTracksRequestUrl.isPresent()) {
            Map<String, String> playlistNameAndTracksRequestUrl = optionalPlaylistNameAndTracksRequestUrl.get();
            String playlistName = playlistNameAndTracksRequestUrl.get("playlistName");
            String playlistHref = playlistNameAndTracksRequestUrl.get("playlistHref");
            String playlistTracksUrlRequest = getTracksRequestUrlByPlaylistHref(playlistHref);

            return new Playlist(playlistName, musicGenre, getSongsByPlaylistTracksUrlRequest(playlistTracksUrlRequest));
        }
        else {
            // TODO: Throw a exception
        }



        return null;
    }

    private List<String> getSongsByPlaylistTracksUrlRequest(String playlistTracksUrlRequest) {

        List<String> songs = new ArrayList<>();

        HttpEntity request = new HttpEntity(getSpotifyRequestHttpHeaders());

        ResponseEntity<String> response =
                restTemplate.exchange(playlistTracksUrlRequest, HttpMethod.GET, request, String.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            String responseJson = response.getBody();

            try {
                final ObjectNode node = new ObjectMapper().readValue(responseJson, ObjectNode.class);
                JsonNode arrNode = node.get("items");
                arrNode.forEach(jsonNode -> {

                    JsonNode trackNode = jsonNode.get("track");
                    String trackName = trackNode.get("name").textValue();
                    JsonNode artistsNode = trackNode.get("album").get("artists");
                    String artistName = artistsNode.get(0).get("name").textValue();


                    songs.add(artistName + " - "+trackName);
                });

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return songs;

    }

    private Optional<Map<String, String>> getFirstPlaylistNameAndTracksRequestUrlByMusicGenre(MusicGenre musicGenre) {
        Map<String, String> firstPlaylistNameAndTracksRequestUrl = new HashMap<>();
        HttpEntity request = new HttpEntity(getSpotifyRequestHttpHeaders());
        ResponseEntity<String> response =
                restTemplate.exchange(getRequestUrlSpotifyBySearch(musicGenre.getDescription()), HttpMethod.GET, request, String.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            String responseJson = response.getBody();

            try {
                final ObjectNode node = new ObjectMapper().readValue(responseJson, ObjectNode.class);
                final JsonNode arrNode = node.get("playlists").get("items");
                String playlistHref = arrNode.get(0).get("href").textValue();
                String playlistName = arrNode.get(0).get("name").textValue();

                firstPlaylistNameAndTracksRequestUrl.put("playlistName", playlistName);
                firstPlaylistNameAndTracksRequestUrl.put("playlistHref", playlistHref);

                return Optional.ofNullable(firstPlaylistNameAndTracksRequestUrl);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        } else {
            // TODO: Throw a exception
        }
        return Optional.empty();
    }

    private String getRequestUrlSpotifyBySearch(String search) {
        StringBuilder spotifyRequestUrl = new StringBuilder();
        spotifyRequestUrl.append(spotifyApiBaseUrl);
        spotifyRequestUrl.append("search?type=playlist&limit=1&q=");
        spotifyRequestUrl.append(search);
        return spotifyRequestUrl.toString();
    }

    private String getTracksRequestUrlByPlaylistHref(String playlistHref) {
        StringBuilder spotifyRequestUrl = new StringBuilder();
        spotifyRequestUrl.append(playlistHref);
        spotifyRequestUrl.append("/tracks");
        spotifyRequestUrl.append(apiProjections());
        spotifyRequestUrl.append("&limit=10");
        return spotifyRequestUrl.toString();
    }

    private HttpHeaders getSpotifyRequestHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+spotifyApiKey);
        return headers;
    }

    private String apiProjections() {
        return "/?fields=items(track(name, album(artists)))";
    }

}

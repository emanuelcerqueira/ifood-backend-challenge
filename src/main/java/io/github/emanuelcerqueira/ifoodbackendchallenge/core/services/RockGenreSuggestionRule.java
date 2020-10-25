package io.github.emanuelcerqueira.ifoodbackendchallenge.core.services;

import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.MusicGenre;
import org.springframework.stereotype.Service;

@Service
public class RockGenreSuggestionRule implements GenreSuggestionRule {

    @Override
    public MusicGenre getMusicGenre() {
        return MusicGenre.ROCK;
    }

    @Override
    public boolean isTemperatureSuitableToMusicGenre(Double temperature) {
        return temperature.intValue() >= 10 && temperature.intValue() <= 14;
    }

}

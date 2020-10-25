package io.github.emanuelcerqueira.ifoodbackendchallenge.core.services;

import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.MusicGenre;
import org.springframework.stereotype.Service;

@Service
public class ClassicalGenreSuggestionRule implements GenreSuggestionRule {

    @Override
    public MusicGenre getMusicGenre() {
        return MusicGenre.CLASSICAL;
    }

    @Override
    public boolean isTemperatureSuitableToMusicGenre(Double temperature) {
        return temperature < 10;
    }
}

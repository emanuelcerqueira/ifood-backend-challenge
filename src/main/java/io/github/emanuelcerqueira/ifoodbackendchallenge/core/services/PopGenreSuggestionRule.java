package io.github.emanuelcerqueira.ifoodbackendchallenge.core.services;

import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.MusicGenre;
import org.springframework.stereotype.Service;

@Service
public class PopGenreSuggestionRule implements GenreSuggestionRule {

    @Override
    public MusicGenre getMusicGenre() {
        return MusicGenre.POP;
    }

    @Override
    public boolean isTemperatureSuitableToMusicGenre(Double temperature) {
        return temperature >= 15 && temperature <= 30;
    }

}

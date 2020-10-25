package io.github.emanuelcerqueira.ifoodbackendchallenge.core.services;

import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.MusicGenre;

public interface GenreSuggestionRule {

    MusicGenre getMusicGenre();
    boolean isTemperatureSuitableToMusicGenre(Double temperature);

}

package io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain;


import com.fasterxml.jackson.annotation.JsonValue;

public enum MusicGenre {

    PARTY("Party"),
    POP("Pop"),
    ROCK("Rock"),
    CLASSICAL("Classical");

    private final String description;

    MusicGenre(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}

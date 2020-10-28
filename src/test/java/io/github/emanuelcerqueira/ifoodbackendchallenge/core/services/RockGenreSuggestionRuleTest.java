package io.github.emanuelcerqueira.ifoodbackendchallenge.core.services;

import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.MusicGenre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RockGenreSuggestionRuleTest {

    @DisplayName("Music genre must be Rock")
    @Test
    void getMusicGenre() {
        GenreSuggestionRule rockRule = new RockGenreSuggestionRule();
        MusicGenre actualMusicGenre = rockRule.getMusicGenre();

        assertEquals(MusicGenre.ROCK, actualMusicGenre);
    }

    @DisplayName("When int value of a temperature is between 10 and 14, returns true, elsewhere returns false")
    @Test
    void isTemperatureSuitableToMusicGenre() {
        GenreSuggestionRule rockRule = new RockGenreSuggestionRule();

        assertTrue(rockRule.isTemperatureSuitableToMusicGenre(10.4d));
        assertTrue(rockRule.isTemperatureSuitableToMusicGenre(13.6d));
        assertTrue(rockRule.isTemperatureSuitableToMusicGenre(14.6d));
        assertFalse(rockRule.isTemperatureSuitableToMusicGenre(9d));
        assertFalse(rockRule.isTemperatureSuitableToMusicGenre(5d));
        assertFalse(rockRule.isTemperatureSuitableToMusicGenre(15d));
        assertFalse(rockRule.isTemperatureSuitableToMusicGenre(20d));

    }
}
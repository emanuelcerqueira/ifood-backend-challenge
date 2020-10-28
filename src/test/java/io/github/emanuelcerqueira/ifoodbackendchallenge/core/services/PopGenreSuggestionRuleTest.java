package io.github.emanuelcerqueira.ifoodbackendchallenge.core.services;

import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.MusicGenre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopGenreSuggestionRuleTest {

    @DisplayName("Music genre must be Pop")
    @Test
    void getMusicGenre() {
        GenreSuggestionRule popRule = new PopGenreSuggestionRule();
        MusicGenre actualMusicGenre = popRule.getMusicGenre();

        assertEquals(MusicGenre.POP, actualMusicGenre);
    }

    @DisplayName("When int value of a temperature is between 15 and 30, returns true, elsewhere returns false")
    @Test
    void isTemperatureSuitableToMusicGenre() {

        GenreSuggestionRule popRule = new PopGenreSuggestionRule();

        assertTrue(popRule.isTemperatureSuitableToMusicGenre(30.5d));
        assertTrue(popRule.isTemperatureSuitableToMusicGenre(25d));
        assertTrue(popRule.isTemperatureSuitableToMusicGenre(15.8d));
        assertFalse(popRule.isTemperatureSuitableToMusicGenre(31.4));
        assertFalse(popRule.isTemperatureSuitableToMusicGenre(40d));
        assertFalse(popRule.isTemperatureSuitableToMusicGenre(14.4));
        assertFalse(popRule.isTemperatureSuitableToMusicGenre(10d));

    }
}
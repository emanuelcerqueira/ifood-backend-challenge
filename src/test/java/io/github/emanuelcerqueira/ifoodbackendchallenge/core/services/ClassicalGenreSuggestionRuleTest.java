package io.github.emanuelcerqueira.ifoodbackendchallenge.core.services;

import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.MusicGenre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassicalGenreSuggestionRuleTest {

    @DisplayName("Music genre must be Classical")
    @Test
    void getMusicGenre() {
        GenreSuggestionRule classicalRule = new ClassicalGenreSuggestionRule();
        MusicGenre actualMusicGenre = classicalRule.getMusicGenre();

        assertEquals(MusicGenre.CLASSICAL, actualMusicGenre);
    }

    @DisplayName("When int value of a temperature is below 10, returns true, elsewhere returns false")
    @Test
    void isTemperatureSuitableToMusicGenre() {
        GenreSuggestionRule classicalRule = new ClassicalGenreSuggestionRule();

        assertTrue(classicalRule.isTemperatureSuitableToMusicGenre(9.5d));
        assertTrue(classicalRule.isTemperatureSuitableToMusicGenre(7d));
        assertFalse(classicalRule.isTemperatureSuitableToMusicGenre(10d));
        assertFalse(classicalRule.isTemperatureSuitableToMusicGenre(30d));
    }
}
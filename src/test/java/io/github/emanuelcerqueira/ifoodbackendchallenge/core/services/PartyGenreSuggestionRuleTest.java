package io.github.emanuelcerqueira.ifoodbackendchallenge.core.services;

import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.MusicGenre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartyGenreSuggestionRuleTest {

    @DisplayName("Music genre must be Party")
    @Test
    void getMusicGenre() {
        GenreSuggestionRule partyRule = new PartyGenreSuggestionRule();
        MusicGenre actualMusicGenre = partyRule.getMusicGenre();

        assertEquals(MusicGenre.PARTY, actualMusicGenre);
    }

    @DisplayName("When int value of a temperature is above 30, returns true, elsewhere returns false")
    @Test
    void isTemperatureSuitableToMusicGenre() {

        GenreSuggestionRule partyRule = new PartyGenreSuggestionRule();

        assertTrue(partyRule.isTemperatureSuitableToMusicGenre(31.4));
        assertTrue(partyRule.isTemperatureSuitableToMusicGenre(40d));
        assertFalse(partyRule.isTemperatureSuitableToMusicGenre(30.5d));
        assertFalse(partyRule.isTemperatureSuitableToMusicGenre(20.8d));
    }
}
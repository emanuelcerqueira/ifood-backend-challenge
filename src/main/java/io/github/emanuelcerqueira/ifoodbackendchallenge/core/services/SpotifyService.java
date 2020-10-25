package io.github.emanuelcerqueira.ifoodbackendchallenge.core.services;

import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.MusicGenre;
import io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain.Playlist;

public interface SpotifyService {

    Playlist findPlaylistByMusicGenre(MusicGenre musicGenre);
}

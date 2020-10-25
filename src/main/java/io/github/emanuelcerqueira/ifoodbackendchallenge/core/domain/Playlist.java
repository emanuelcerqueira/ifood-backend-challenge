package io.github.emanuelcerqueira.ifoodbackendchallenge.core.domain;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String name;
    private MusicGenre musicGenre;
    private List<String> songs = new ArrayList<>();

    public Playlist() {
    }

    public Playlist(String name, MusicGenre musicGenre, List<String> songs) {
        this.name = name;
        this.musicGenre = musicGenre;
        this.songs = songs;
    }

    public List<String> getSongs() {
        return songs;
    }

    public MusicGenre getMusicGenre() {
        return musicGenre;
    }

    public String getName() {
        return name;
    }
}

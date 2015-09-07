package com.gstv.scott.lastfm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sfhorton on 9/6/2015.
 */
@Data
@JsonIgnoreProperties
public class TopAlbums {

    private String artist;
    private Collection<Album> album;

    public TopAlbums(String artist, Collection<Album> album) {
        this.artist = artist;
        this.album = album;
    }

    public Collection<Album> getAlbum() {
        return album;
    }
}

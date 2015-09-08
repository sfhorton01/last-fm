package com.gstv.scott.lastfm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sfhorton on 9/6/2015.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopAlbums {

    private String artist;
    @JsonDeserialize(contentAs = Album.class)
    @JsonSerialize(contentAs = Album.class)
    private Collection<Album> album;

    public TopAlbums() {
    }

    public TopAlbums(String artist, Collection<Album> album) {
        this.artist = artist;
        this.album = album;
    }

    public Collection<Album> getAlbum() {
        return album;
    }
}

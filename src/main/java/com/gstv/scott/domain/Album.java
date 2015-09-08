package com.gstv.scott.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Collection;

public class Album {

    public static final Album NO_ARTIST = new Album("12345", "Artist Not Found");
    private final String id;
    private final String name;
    @JsonDeserialize(contentAs = Track.class)
    @JsonSerialize(contentAs = Track.class)
    private Collection<Track> tracks;

    public Album(String mbid, String name) {

        this.id = mbid;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Collection<Track> tracks) {
        this.tracks = tracks;
    }
}

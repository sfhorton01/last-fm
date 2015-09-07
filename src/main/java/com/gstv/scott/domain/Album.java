package com.gstv.scott.domain;

import java.util.Collection;

public class Album {

    public static final Album NO_ARTIST = new Album("12345", "Artist Not Found");
    private final String id;
    private final String name;
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

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
public class Album {

    private String name;
    private String mbid;
    private String rank;
    private Collection<Track> tracks;

    public Album() {}

    public Album(String mbid, String name) {
        this.mbid = mbid;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getMbid() {
        return mbid;
    }

    public Collection<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Collection<Track> tracks) {
        this.tracks = tracks;
    }
}
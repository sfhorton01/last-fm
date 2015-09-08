package com.gstv.scott.lastfm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {

    private String name;
    private String mbid;
    private String rank;
    @JsonDeserialize(contentAs = Track.class)
    @JsonSerialize(contentAs = Track.class)
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
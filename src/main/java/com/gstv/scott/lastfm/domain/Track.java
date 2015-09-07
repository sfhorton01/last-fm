package com.gstv.scott.lastfm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Track {

    private String name;

    public Track() {}

    public Track(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

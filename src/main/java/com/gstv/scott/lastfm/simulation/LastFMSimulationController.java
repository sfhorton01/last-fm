package com.gstv.scott.lastfm.simulation;

import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.gstv.scott.lastfm.domain.Album;
import com.gstv.scott.lastfm.domain.TopAlbums;
import com.gstv.scott.lastfm.domain.Track;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sfhorton on 9/7/2015.
 */
@RestController
public class LastFMSimulationController {

    @RequestMapping("/simulation")
    public Object simulate(@RequestParam(value="method") String method, @RequestParam(value="artist", required = false) String artist, @RequestParam(value="mbid", required = false) String mbid) {
        // todo split this up and read json files from resources directory
        if ("artist.getTopAlbums".equals(method)) {
           return new TopAlbums(artist, Lists.<Album>newArrayList(new Album("12345", "some album")));
        } else if ("album.getInfo".equals(method)) {
            Album album = new Album(mbid, "some album");
            album.setTracks(Lists.newArrayList(new Track("some track")));
            return album;
        } else {
            return null;
        }
    }
}

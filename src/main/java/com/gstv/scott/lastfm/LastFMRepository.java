package com.gstv.scott.lastfm;

import com.google.common.collect.Lists;
import com.gstv.scott.core.MusicRepository;
import com.gstv.scott.domain.Album;
import com.gstv.scott.domain.Track;
import com.gstv.scott.lastfm.domain.TopAlbums;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * Created by sfhorton on 9/6/2015.
 */
public class LastFMRepository implements MusicRepository {
    private RestTemplate lastFMTemplate;
    private String fmRepoUrl;
    private String apiKey;

    public LastFMRepository(RestTemplate restTemplate, String fmRepoUrl, String apiKey) {
        lastFMTemplate = restTemplate;
        this.fmRepoUrl = fmRepoUrl;
        this.apiKey = apiKey;
    }

    @Override
    public Collection<Album> getTopAlbums(String artist) {
        String url = fmRepoUrl + "&method=artist.getTopAlbums&artist=" + artist + "&api_key=" + apiKey;
        TopAlbums topAlbums = lastFMTemplate.getForObject(url, TopAlbums.class);
        Collection<Album> albums = Lists.newArrayList();
        for (com.gstv.scott.lastfm.domain.Album album : topAlbums.getAlbum()) {
            albums.add(new Album(album.getMbid(), album.getName()));
        }
        return albums;
    }

    @Override
    public Album getAlbumInfo(String id) {
        String url = fmRepoUrl + "&method=album.getInfo&mbid=" + id + "&api_key=" + apiKey;
        com.gstv.scott.lastfm.domain.Album album = lastFMTemplate.getForObject(url, com.gstv.scott.lastfm.domain.Album.class);
        Album albumInfo = new Album(album.getMbid(), album.getName());
        Collection<Track> tracks = Lists.newArrayList();
        for (com.gstv.scott.lastfm.domain.Track track : album.getTracks()) {
            Track trackInfo = new Track(track.getName());
            tracks.add(trackInfo);
        }
        albumInfo.setTracks(tracks);
        return albumInfo;
    }
}

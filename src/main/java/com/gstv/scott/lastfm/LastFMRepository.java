package com.gstv.scott.lastfm;

import com.google.common.collect.Lists;
import com.gstv.scott.core.MusicRepository;
import com.gstv.scott.domain.Album;
import com.gstv.scott.domain.Track;
import com.gstv.scott.lastfm.domain.TopAlbums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * A MusicRepository that leverages the last.fm api's to retrieve musical information.
 */
public class LastFMRepository implements MusicRepository {

    private static final Logger log = LoggerFactory.getLogger(LastFMRepository.class);
    private RestTemplate lastFMTemplate;
    private String fmRepoUrl;
    private String apiKey;
    private String limit = "10";

    public LastFMRepository(RestTemplate restTemplate, String fmRepoUrl, String apiKey) {
        lastFMTemplate = restTemplate;
        this.fmRepoUrl = fmRepoUrl;
        this.apiKey = apiKey;
    }

    @Override
    public Collection<Album> getTopAlbums(String artist) {
        String url = fmRepoUrl + "&method=artist.getTopAlbums&artist=" + artist + "&limit=" + limit + "&api_key=" + apiKey;
        Collection<Album> albums = Lists.newArrayList();
        try {
            TopAlbums topAlbums = lastFMTemplate.getForObject(url, TopAlbums.class);
            for (com.gstv.scott.lastfm.domain.Album album : topAlbums.getAlbum()) {
                albums.add(new Album(album.getMbid(), album.getName()));
            }
        } catch (HttpClientErrorException e) {
            log.error("last.fm is unhappy", e);
        }
        return albums;
    }

    @Override
    public Album getAlbumInfo(String id) {
        String url = fmRepoUrl + "&method=album.getInfo&mbid=" + id + "&api_key=" + apiKey;
        try {
            com.gstv.scott.lastfm.domain.Album album = lastFMTemplate.getForObject(url, com.gstv.scott.lastfm.domain.Album.class);
            Album albumInfo = new Album(album.getMbid(), album.getName());
            Collection<Track> tracks = Lists.newArrayList();
            for (com.gstv.scott.lastfm.domain.Track track : album.getTracks()) {
                Track trackInfo = new Track(track.getName());
                tracks.add(trackInfo);
            }
            albumInfo.setTracks(tracks);
            return albumInfo;
        } catch (HttpClientErrorException e) {
            log.error("last.fm is unhappy", e);
            return null;
        }
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}

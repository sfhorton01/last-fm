package com.gstv.scott.api;

import com.google.common.collect.Lists;
import com.gstv.scott.core.MusicRepository;
import com.gstv.scott.domain.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by sfhorton on 9/6/2015.
 */
@RestController
public class MusicInformationController {

    @Autowired
    private MusicRepository musicRepository;

    public MusicInformationController() {}

    public MusicInformationController(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @RequestMapping("/topalbums/{artist}")
    public Collection<Album> getTopAlbums(@PathVariable String artist) {
        // todo some validation
        Collection<Album> topAlbums = musicRepository.getTopAlbums(artist);
        Collection<Album> albums = Lists.newArrayList();
        for (Album topAlbum : topAlbums) {
            albums.add(musicRepository.getAlbumInfo(topAlbum.getId()));
        }
        return albums;
    }

    public void setMusicRepository(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }
}

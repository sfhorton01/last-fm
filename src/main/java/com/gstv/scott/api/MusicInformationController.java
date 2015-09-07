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
 * MusicInformationController provides methods that combine MusicRepository functionality
 * in useful ways.
 *
 */
@RestController
public class MusicInformationController {

    @Autowired
    private MusicRepository musicRepository;

    public MusicInformationController() {}

    public MusicInformationController(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    /**
     * getTopAlbums retrieves an Artist's top ten albums from the MusicRepository including the albums'
     * track names.
     *
     * @param artist String the name of the artist
     * @return Collection&lt;Album&gt;  list of the top ten albums for the artist
     */
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

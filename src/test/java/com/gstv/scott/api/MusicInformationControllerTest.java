package com.gstv.scott.api;

import com.google.common.collect.Lists;
import com.gstv.scott.core.MusicRepository;
import com.gstv.scott.domain.Album;
import com.gstv.scott.domain.Track;
import com.gstv.scott.lastfm.LastFMRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;

import java.util.Collection;

import static org.junit.Assert.*;

public class MusicInformationControllerTest {

    private MusicRepository musicRepository;
    private final Mockery context = new Mockery();
    private Album album;

    @org.junit.Before
    public void setUp() throws Exception {
        musicRepository = context.mock(MusicRepository.class);
        album = new Album("12345", "Believe");
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testGetTopAlbums() throws Exception {
        String artist = "Cher";

        context.checking(new Expectations() {{
            oneOf(musicRepository).getTopAlbums("Cher"); will(returnValue(Lists.newArrayList(album)));
            atLeast(1).of(musicRepository).getAlbumInfo("12345"); will(returnValue(album));
        }});
        Collection<Album> albums = new MusicInformationController(musicRepository).getTopAlbums(artist);

        assertNotNull(albums);
        assertFalse(albums.isEmpty());

        Album topAlbum = albums.iterator().next();
        assertEquals(album, topAlbum);

        context.assertIsSatisfied();
    }
}
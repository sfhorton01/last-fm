package com.gstv.scott.lastfm;

import com.google.common.collect.Lists;
import com.gstv.scott.domain.Album;
import com.gstv.scott.lastfm.domain.TopAlbums;
import com.gstv.scott.lastfm.domain.Track;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static org.junit.Assert.*;

public class LastFMRepositoryTest {

    public static final String ALBUM_INFO = "<album>\n" +
            "  <name>Believe</name>\n" +
            "  <artist>Cher</artist>\n" +
            "  <id>2026126</id>\n" +
            "  <mbid>61bf0388-b8a9-48f4-81d1-7eb02706dfb0</mbid>\n" +
            "  <url>http://www.last.fm/music/Cher/Believe</url>\n" +
            "  <releasedate>6 Apr 1999, 00:00</releasedate>\n" +
            "  <image size=\"small\">...</image>\n" +
            "  <image size=\"medium\">...</image>\n" +
            "  <image size=\"large\">...</image>\n" +
            "  <listeners>47602</listeners>\n" +
            "  <playcount>212991</playcount>\n" +
            "  <toptags>\n" +
            "    <tag>\n" +
            "      <name>pop</name>\n" +
            "      <url>http://www.last.fm/tag/pop</url>\n" +
            "    </tag>\n" +
            "  </toptags>\n" +
            "  <tracks>\n" +
            "    <track rank=\"1\">\n" +
            "      <name>Believe</name>\n" +
            "      <duration>239</duration>\n" +
            "      <mbid/>\n" +
            "      <url>http://www.last.fm/music/Cher/_/Believe</url>\n" +
            "      <streamable fulltrack=\"0\">1</streamable>\n" +
            "      <artist>\n" +
            "        <name>Cher</name>\n" +
            "        <mbid>bfcc6d75-a6a5-4bc6-8282-47aec8531818</mbid>\n" +
            "        <url>http://www.last.fm/music/Cher</url>\n" +
            "      </artist>\n" +
            "    </track>\n" +
            "  </tracks>\n" +
            "</album>";

    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    private final RestTemplate restTemplate = context.mock(RestTemplate.class);
    private LastFMRepository lastFMRepository;
    private TopAlbums topAlbums;
    private com.gstv.scott.lastfm.domain.Album album;

    @Before
    public void setUp() throws Exception {
        lastFMRepository = new LastFMRepository(restTemplate, "http://last.fm/api?format=json", "12345");
        album = new com.gstv.scott.lastfm.domain.Album("12345", "Believe");
        topAlbums = new TopAlbums("Cher", Lists.newArrayList(album));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetTopAlbums() throws Exception {
        context.checking(new Expectations() {{
            oneOf(restTemplate).getForObject("http://last.fm/api?format=json&method=artist.getTopAlbums&artist=Cher&api_key=12345", TopAlbums.class); will(returnValue(topAlbums));
        }});

        Collection<Album> albums = lastFMRepository.getTopAlbums("Cher");

        assertNotNull(albums);
        assertFalse(albums.isEmpty());

        assertEquals("Believe", albums.iterator().next().getName());
        context.assertIsSatisfied();
    }

    @Test
    public void testGetTopAlbumsKablooey() throws Exception {
        context.checking(new Expectations() {{
            oneOf(restTemplate).getForObject("http://last.fm/api?format=json&method=artist.getTopAlbums&artist=Cher&api_key=12345", TopAlbums.class); will(throwException(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR)));
        }});

        Collection<Album> albums = lastFMRepository.getTopAlbums("Cher");

        assertNotNull(albums);
        assertTrue(albums.isEmpty());

        context.assertIsSatisfied();
    }

    @Test
    public void testGetAlbumInfo() throws Exception {

        album.setTracks(Lists.newArrayList(new Track("Believe")));
        context.checking(new Expectations() {{
            oneOf(restTemplate).getForObject("http://last.fm/api?format=json&method=album.getInfo&mbid=12345&api_key=12345", com.gstv.scott.lastfm.domain.Album.class); will(returnValue(album));
        }});

        String id = "12345";
        Album albumInfo = lastFMRepository.getAlbumInfo(id);

        assertNotNull(albumInfo);
        assertEquals("Believe", albumInfo.getName());
        assertNotNull(albumInfo.getTracks());
        assertFalse(albumInfo.getTracks().isEmpty());
        assertEquals("Believe", albumInfo.getTracks().iterator().next().getName());

        context.assertIsSatisfied();
    }

    @Test
    public void testGetAlbumInfoKablooey() throws Exception {
        album.setTracks(Lists.newArrayList(new Track("Believe")));
        context.checking(new Expectations() {{
            oneOf(restTemplate).getForObject("http://last.fm/api?format=json&method=album.getInfo&mbid=12345&api_key=12345", com.gstv.scott.lastfm.domain.Album.class); will(throwException(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR)));
        }});

        String id = "12345";
        Album albumInfo = lastFMRepository.getAlbumInfo(id);

        assertNull(albumInfo);

        context.assertIsSatisfied();
    }
}
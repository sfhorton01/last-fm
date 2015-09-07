package com.gstv.scott.core;

import com.gstv.scott.domain.Album;

import java.util.Collection;

/**
 * Interface that defines functionality expected of a repository containing music information
 */
public interface MusicRepository {

    Collection<Album> getTopAlbums(String artist);

    Album getAlbumInfo(String id);
}

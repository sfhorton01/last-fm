package com.gstv.scott.core;

import com.gstv.scott.domain.Album;

import java.util.Collection;

/**
 * Created by sfhorton on 9/6/2015.
 */
public interface MusicRepository {

    Collection<Album> getTopAlbums(String artist);

    Album getAlbumInfo(String id);
}

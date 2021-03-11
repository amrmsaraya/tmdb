package com.github.amrmsaraya.tmdb.data.repository.artist.datasourceImpl

import com.github.amrmsaraya.tmdb.data.model.artist.Artist
import com.github.amrmsaraya.tmdb.data.model.artist.ArtistList
import com.github.amrmsaraya.tmdb.data.repository.artist.datasource.ArtistCacheDataSource

class ArtistCacheDataSourceImpl : ArtistCacheDataSource {

    private var popularArtists: ArtistList? = null

    private var artists = mutableListOf<Artist>()

    override suspend fun getArtistsFromCache(): ArtistList? = popularArtists

    override suspend fun getArtistFromCache(): List<Artist> = artists


    override suspend fun saveArtistsToCache(artists: ArtistList) {
        popularArtists = artists
    }

    override suspend fun saveArtistToCache(artist: Artist) {
        artists.add(artist)
    }
}

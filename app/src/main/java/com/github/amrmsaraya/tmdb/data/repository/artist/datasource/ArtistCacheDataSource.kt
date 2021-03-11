package com.github.amrmsaraya.tmdb.data.repository.artist.datasource

import com.github.amrmsaraya.tmdb.data.model.artist.Artist
import com.github.amrmsaraya.tmdb.data.model.artist.ArtistList

interface ArtistCacheDataSource {
    suspend fun getArtistsFromCache(): ArtistList?
    suspend fun getArtistFromCache(): List<Artist>
    suspend fun saveArtistsToCache(artists: ArtistList)
    suspend fun saveArtistToCache(artist: Artist)
}

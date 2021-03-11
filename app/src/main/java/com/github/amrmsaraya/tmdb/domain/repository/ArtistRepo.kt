package com.github.amrmsaraya.tmdb.domain.repository

import com.github.amrmsaraya.tmdb.data.model.artist.Artist
import com.github.amrmsaraya.tmdb.data.model.artist.ArtistList

interface ArtistRepo {
    suspend fun getArtist(id: Int): Artist?
    suspend fun getArtists(): ArtistList?
    suspend fun updateArtist(id: Int): Artist?
    suspend fun updateArtists(): ArtistList?
}

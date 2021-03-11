package com.github.amrmsaraya.tmdb.data.repository.artist.datasource

import com.github.amrmsaraya.tmdb.data.model.artist.Artist
import com.github.amrmsaraya.tmdb.data.model.artist.ArtistList

interface ArtistLocalDataSource {
    suspend fun getArtistsFromDB(): ArtistList
    suspend fun getArtistFromDB(id: Int): Artist
    suspend fun insertArtistsToDB(artists: ArtistList)
    suspend fun insertArtistToDB(artist: Artist)
}

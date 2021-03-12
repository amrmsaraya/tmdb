package com.github.amrmsaraya.tmdb.data.repository.artist.datasource

import com.github.amrmsaraya.tmdb.data.model.artist.Artist
import com.github.amrmsaraya.tmdb.data.model.artist.ArtistList
import com.github.amrmsaraya.tmdb.data.model.artist.Credit
import retrofit2.Response

interface ArtistRemoteDataSource {
    suspend fun getArtists(): Response<ArtistList>
    suspend fun getArtist(id: Int): Response<Artist>
    suspend fun getCredit(id: Int): Response<Credit>
}

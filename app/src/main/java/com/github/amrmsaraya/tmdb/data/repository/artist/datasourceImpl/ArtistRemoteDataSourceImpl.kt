package com.github.amrmsaraya.tmdb.data.repository.artist.datasourceImpl

import com.github.amrmsaraya.tmdb.data.model.artist.Artist
import com.github.amrmsaraya.tmdb.data.model.artist.ArtistList
import com.github.amrmsaraya.tmdb.data.remote.TMDBService
import com.github.amrmsaraya.tmdb.data.repository.artist.datasource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(private val tmdbService: TMDBService, private val apiKey: String) :
    ArtistRemoteDataSource {
    override suspend fun getArtists(): Response<ArtistList> {
        return tmdbService.getPopularArtists(apiKey)
    }

    override suspend fun getArtist(id: Int): Response<Artist> {
        return tmdbService.getArtist(id, apiKey)
    }
}

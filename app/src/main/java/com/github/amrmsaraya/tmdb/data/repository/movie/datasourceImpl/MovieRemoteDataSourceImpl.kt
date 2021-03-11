package com.github.amrmsaraya.tmdb.data.repository.movie.datasourceImpl

import com.github.amrmsaraya.tmdb.data.model.movie.Movie
import com.github.amrmsaraya.tmdb.data.model.movie.MovieList
import com.github.amrmsaraya.tmdb.data.remote.TMDBService
import com.github.amrmsaraya.tmdb.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(private val tmdbService: TMDBService, private val apiKey: String) :
    MovieRemoteDataSource {
    override suspend fun getMovies(category: String): Response<MovieList> {
        return tmdbService.getMovies(category, apiKey)
    }

    override suspend fun getMovie(id: Int): Response<Movie> {
        return tmdbService.getMovie(id, apiKey)
    }
}

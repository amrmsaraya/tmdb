package com.github.amrmsaraya.tmdb.data.repository.movie.datasource

import com.github.amrmsaraya.tmdb.data.model.cast.CastList
import com.github.amrmsaraya.tmdb.data.model.movie.Movie
import com.github.amrmsaraya.tmdb.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies(category: String): Response<MovieList>
    suspend fun getMovie(id: Int): Response<Movie>
    suspend fun getCast(id: Int): Response<CastList>
}

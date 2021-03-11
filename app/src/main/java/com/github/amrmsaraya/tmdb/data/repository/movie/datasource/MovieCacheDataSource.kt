package com.github.amrmsaraya.tmdb.data.repository.movie.datasource

import com.github.amrmsaraya.tmdb.data.model.movie.Movie
import com.github.amrmsaraya.tmdb.data.model.movie.MovieList

interface MovieCacheDataSource {
    suspend fun getMoviesFromCache(category: String): MovieList?
    suspend fun getMovieFromCache(): List<Movie>
    suspend fun saveMoviesToCache(movies: MovieList, category: String)
    suspend fun saveMovieToCache(movie: Movie)
}

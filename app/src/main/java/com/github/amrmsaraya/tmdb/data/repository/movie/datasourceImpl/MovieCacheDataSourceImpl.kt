package com.github.amrmsaraya.tmdb.data.repository.movie.datasourceImpl

import com.github.amrmsaraya.tmdb.data.model.movie.Movie
import com.github.amrmsaraya.tmdb.data.model.movie.MovieList
import com.github.amrmsaraya.tmdb.data.repository.movie.datasource.MovieCacheDataSource

class MovieCacheDataSourceImpl : MovieCacheDataSource {

    private var popularMovies: MovieList? = null
    private var topRatedMovies: MovieList? = null
    private var upcomingMovies: MovieList? = null
    private var movies = mutableListOf<Movie>()

    override suspend fun getMoviesFromCache(category: String): MovieList? {
        return when (category) {
            "popular" -> popularMovies
            "topRated" -> topRatedMovies
            "upcoming" -> upcomingMovies
            else -> null
        }
    }

    override suspend fun getMovieFromCache(): List<Movie> {
        return movies
    }

    override suspend fun saveMoviesToCache(movies: MovieList, category: String) {
        when (category) {
            "popular" -> popularMovies = movies
            "topRated" -> topRatedMovies = movies
            "upcoming" -> upcomingMovies = movies
        }
    }

    override suspend fun saveMovieToCache(movie: Movie) {
        movies.add(movie)
    }
}

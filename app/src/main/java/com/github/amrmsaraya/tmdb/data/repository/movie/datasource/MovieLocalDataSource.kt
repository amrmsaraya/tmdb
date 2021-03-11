package com.github.amrmsaraya.tmdb.data.repository.movie.datasource

import com.github.amrmsaraya.tmdb.data.model.movie.Movie
import com.github.amrmsaraya.tmdb.data.model.movie.MovieList

interface MovieLocalDataSource {
    suspend fun getMoviesFromDB(category: String): MovieList
    suspend fun getMovieFromDB(id: Int): Movie
    suspend fun insertMoviesToDB(movies: MovieList)
    suspend fun insertMovieToDB(movie: Movie)
}

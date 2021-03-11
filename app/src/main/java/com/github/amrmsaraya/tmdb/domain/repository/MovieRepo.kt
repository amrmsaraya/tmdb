package com.github.amrmsaraya.tmdb.domain.repository

import com.github.amrmsaraya.tmdb.data.model.movie.Movie
import com.github.amrmsaraya.tmdb.data.model.movie.MovieList

interface MovieRepo {
    suspend fun getMovie(id: Int): Movie?
    suspend fun getMovies(category: String): MovieList?
    suspend fun updateMovie(id: Int): Movie?
    suspend fun updateMovies(category: String): MovieList?
}

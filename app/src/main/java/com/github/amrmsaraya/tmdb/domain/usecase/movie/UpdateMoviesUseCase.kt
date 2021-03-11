package com.github.amrmsaraya.tmdb.domain.usecase.movie

import com.github.amrmsaraya.tmdb.data.model.movie.MovieList
import com.github.amrmsaraya.tmdb.domain.repository.MovieRepo

class UpdateMoviesUseCase(private val movieRepo: MovieRepo) {
    suspend fun execute(category: String): MovieList? = movieRepo.updateMovies(category)
}

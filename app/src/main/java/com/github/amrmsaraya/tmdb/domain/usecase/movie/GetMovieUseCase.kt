package com.github.amrmsaraya.tmdb.domain.usecase.movie

import com.github.amrmsaraya.tmdb.data.model.movie.Movie
import com.github.amrmsaraya.tmdb.domain.repository.MovieRepo

class GetMovieUseCase(private val movieRepo: MovieRepo) {
    suspend fun execute(id: Int): Movie? = movieRepo.getMovie(id)
}

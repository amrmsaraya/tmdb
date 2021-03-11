package com.github.amrmsaraya.tmdb.data.repository.movie.datasourceImpl

import com.github.amrmsaraya.tmdb.data.local.MovieDao
import com.github.amrmsaraya.tmdb.data.model.movie.Movie
import com.github.amrmsaraya.tmdb.data.model.movie.MovieList
import com.github.amrmsaraya.tmdb.data.repository.movie.datasource.MovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override suspend fun getMoviesFromDB(category: String): MovieList {
        return movieDao.getMovieList(category)
    }

    override suspend fun getMovieFromDB(id: Int): Movie {
        return movieDao.getMovie(id)
    }

    override suspend fun insertMoviesToDB(movies: MovieList) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.insertMovieList(movies)
        }
    }

    override suspend fun insertMovieToDB(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.insertMovie(movie)
        }
    }
}

package com.github.amrmsaraya.tmdb.data.repository.movie

import android.util.Log
import com.github.amrmsaraya.tmdb.data.model.movie.Movie
import com.github.amrmsaraya.tmdb.data.model.movie.MovieList
import com.github.amrmsaraya.tmdb.data.repository.movie.datasource.MovieCacheDataSource
import com.github.amrmsaraya.tmdb.data.repository.movie.datasource.MovieLocalDataSource
import com.github.amrmsaraya.tmdb.data.repository.movie.datasource.MovieRemoteDataSource
import com.github.amrmsaraya.tmdb.domain.repository.MovieRepo

class MovieRepoImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepo {
    override suspend fun getMovie(id: Int): Movie? {
        TODO("Not yet implemented")
    }

    override suspend fun getMovies(category: String): MovieList? {
        return getMoviesFromCache(category)
    }

    override suspend fun updateMovie(id: Int): Movie? {
        TODO("Not yet implemented")
    }

    override suspend fun updateMovies(category: String): MovieList? {
        val movieList = getMoviesFromAPI(category)
        if (movieList != null) {
            movieLocalDataSource.insertMoviesToDB(movieList)
            movieCacheDataSource.saveMoviesToCache(movieList, category)
        }
        return movieList
    }

    suspend fun getMoviesFromAPI(category: String): MovieList? {
        return try {
            val response = movieRemoteDataSource.getMovies(category)
            val body = response.body()
            if (body != null) {
                body.category = category
            }
            body
        } catch (exception: Exception) {
            Log.i("myTag", exception.message.toString())
            null
        }
    }

    suspend fun getMoviesFromDB(category: String): MovieList? {
        var movieList: MovieList? = null
        try {
            movieList = movieLocalDataSource.getMoviesFromDB(category)
        } catch (exception: Exception) {
            Log.i("myTag", exception.message.toString())
        }
        if (movieList != null) {
            return movieList
        } else {
            movieList = getMoviesFromAPI(category)
            if (movieList != null) {
                movieLocalDataSource.insertMoviesToDB(movieList)
            }
        }
        return movieList
    }

    suspend fun getMoviesFromCache(category: String): MovieList? {
        var movieList: MovieList? = null
        try {
            movieList = movieCacheDataSource.getMoviesFromCache(category)
        } catch (exception: Exception) {
            Log.i("myTag", exception.message.toString())
        }
        if (movieList != null) {
            return movieList
        } else {
            movieList = getMoviesFromDB(category)
            if (movieList != null) {
                movieCacheDataSource.saveMoviesToCache(movieList, category)
            }
        }
        return movieList
    }
}

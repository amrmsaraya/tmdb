package com.github.amrmsaraya.tmdb.data.local

import androidx.room.*
import com.github.amrmsaraya.tmdb.data.model.movie.Movie
import com.github.amrmsaraya.tmdb.data.model.movie.MovieList

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movies: MovieList)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Update
    suspend fun updateMovieList(movies: MovieList)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movie WHERE id=:id")
    suspend fun getMovie(id: Int): Movie

    @Query("SELECT * FROM movie_List WHERE category=:category")
    suspend fun getMovieList(category: String): MovieList


}

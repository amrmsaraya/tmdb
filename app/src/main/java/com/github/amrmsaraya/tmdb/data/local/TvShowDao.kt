package com.github.amrmsaraya.tmdb.data.local

import androidx.room.*
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShow
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShowList

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow: TvShow)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShowList(tvShows: TvShowList)

    @Update
    suspend fun updateTvShow(tvShow: TvShow)

    @Update
    suspend fun updateTvShowList(tvShows: TvShowList)

    @Delete
    suspend fun deleteTvShow(tvShow: TvShow)

    @Delete
    suspend fun deleteTvShowList(tvShow: TvShowList)

    @Query("SELECT * FROM tvShow WHERE id=:id")
    suspend fun getTvShow(id: Int): TvShow

    @Query("SELECT * FROM tvShow_list WHERE category=:category")
    suspend fun getTvShowList(category: String): TvShowList


}

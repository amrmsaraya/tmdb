package com.github.amrmsaraya.tmdb.data.repository.tvshow.datasource

import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShow
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShowList

interface TvShowLocalDataSource {
    suspend fun getTvShowsFromDB(category: String): TvShowList
    suspend fun getTvShowFromDB(id: Int): TvShow
    suspend fun insertTvShowsToDB(tvShows: TvShowList)
    suspend fun insertTvShowToDB(tvShow: TvShow)
}

package com.github.amrmsaraya.tmdb.data.repository.tvshow.datasource

import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShow
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShowList

interface TvShowCacheDataSource {
    suspend fun getTvShowsFromCache(category: String): TvShowList?
    suspend fun getTvShowFromCache(): List<TvShow>
    suspend fun saveTvShowsToCache(tvShows: TvShowList, category: String)
    suspend fun saveTvShowToCache(tvShow: TvShow)
}

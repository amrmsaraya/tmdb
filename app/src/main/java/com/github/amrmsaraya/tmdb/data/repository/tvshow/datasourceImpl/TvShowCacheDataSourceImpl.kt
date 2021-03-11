package com.github.amrmsaraya.tmdb.data.repository.tvshow.datasourceImpl

import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShow
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShowList
import com.github.amrmsaraya.tmdb.data.repository.tvshow.datasource.TvShowCacheDataSource

class TvShowCacheDataSourceImpl : TvShowCacheDataSource {

    private var popularTvShows: TvShowList? = null
    private var topRatedTvShows: TvShowList? = null
    private var upcomingTvShows: TvShowList? = null
    private var tvShows = mutableListOf<TvShow>()

    override suspend fun getTvShowsFromCache(category: String): TvShowList? {
        return when (category) {
            "popular" -> popularTvShows
            "topRated" -> topRatedTvShows
            "upcoming" -> upcomingTvShows
            else -> null
        }
    }

    override suspend fun getTvShowFromCache(): List<TvShow> {
        return tvShows
    }

    override suspend fun saveTvShowsToCache(tvShows: TvShowList, category: String) {
        when (category) {
            "popular" -> popularTvShows = tvShows
            "topRated" -> topRatedTvShows = tvShows
            "upcoming" -> upcomingTvShows = tvShows
        }
    }

    override suspend fun saveTvShowToCache(tvShow: TvShow) {
        tvShows.add(tvShow)
    }
}

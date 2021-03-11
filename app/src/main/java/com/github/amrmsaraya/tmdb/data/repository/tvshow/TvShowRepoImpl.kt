package com.github.amrmsaraya.tmdb.data.repository.tvshow

import android.util.Log
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShow
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShowList
import com.github.amrmsaraya.tmdb.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.github.amrmsaraya.tmdb.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.github.amrmsaraya.tmdb.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.github.amrmsaraya.tmdb.domain.repository.TvShowRepo

class TvShowRepoImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
) : TvShowRepo {
    override suspend fun getTvShow(id: Int): TvShow? {
        TODO("Not yet implemented")
    }

    override suspend fun getTvShows(category: String): TvShowList? {
        return getTvShowsFromCache(category)
    }

    override suspend fun updateTvShow(id: Int): TvShow? {
        TODO("Not yet implemented")
    }

    override suspend fun updateTvShows(category: String): TvShowList? {
        val tvShowList = getTvShowsFromAPI(category)
        if (tvShowList != null) {
            tvShowLocalDataSource.insertTvShowsToDB(tvShowList)
            tvShowCacheDataSource.saveTvShowsToCache(tvShowList, category)
        }
        return tvShowList
    }

    private suspend fun getTvShowsFromAPI(category: String): TvShowList? {
        return try {
            val response = tvShowRemoteDataSource.getTvShows(category)
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

    private suspend fun getTvShowsFromDB(category: String): TvShowList? {
        var tvShowList: TvShowList? = null
        try {
            tvShowList = tvShowLocalDataSource.getTvShowsFromDB(category)
        } catch (exception: Exception) {
            Log.i("myTag", exception.message.toString())
        }
        if (tvShowList != null) {
            return tvShowList
        } else {
            tvShowList = getTvShowsFromAPI(category)
            if (tvShowList != null) {
                tvShowLocalDataSource.insertTvShowsToDB(tvShowList)
            }
        }
        return tvShowList
    }

    private suspend fun getTvShowsFromCache(category: String): TvShowList? {
        var tvShowList: TvShowList? = null
        try {
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache(category)
        } catch (exception: Exception) {
            Log.i("myTag", exception.message.toString())
        }
        if (tvShowList != null) {
            return tvShowList
        } else {
            tvShowList = getTvShowsFromDB(category)
            if (tvShowList != null) {
                tvShowCacheDataSource.saveTvShowsToCache(tvShowList, category)
            }
        }
        return tvShowList
    }
}

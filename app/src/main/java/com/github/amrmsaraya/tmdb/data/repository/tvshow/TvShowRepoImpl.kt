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
        return getTvShowFromCache(id)
    }

    override suspend fun getTvShows(category: String): TvShowList? {
        return getTvShowsFromCache(category)
    }

    override suspend fun updateTvShow(id: Int): TvShow? {
        val tvShow = getTvShowFromAPI(id)
        if (tvShow != null) {
            tvShowLocalDataSource.insertTvShowToDB(tvShow)
            tvShowCacheDataSource.saveTvShowToCache(tvShow)
        }
        return tvShow
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

    suspend fun getTvShowFromAPI(id: Int): TvShow? {
        return try {
            val response = tvShowRemoteDataSource.getTvShow(id)
            val body = response.body()
            if (body != null) {
                val castListResponse = tvShowRemoteDataSource.getCast(id)
                if (castListResponse.body() != null) {
                    body.castList = castListResponse.body()
                }
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

    suspend fun getTvShowFromDB(id: Int): TvShow? {
        var tvShow: TvShow? = null
        try {
            tvShow = tvShowLocalDataSource.getTvShowFromDB(id)
        } catch (exception: Exception) {
            Log.i("myTag", exception.message.toString())
        }
        if (tvShow != null) {
            return tvShow
        } else {
            tvShow = getTvShowFromAPI(id)
            if (tvShow != null) {
                tvShowLocalDataSource.insertTvShowToDB(tvShow)
            }
        }
        return tvShow
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

    suspend fun getTvShowFromCache(id: Int): TvShow? {
        var tvShowList = emptyList<TvShow>()
        try {
            tvShowList = tvShowCacheDataSource.getTvShowFromCache()
        } catch (exception: Exception) {
            Log.i("myTag", exception.message.toString())
        }
        if (tvShowList.isNotEmpty()) {
            tvShowList.forEach {
                if (it.id == id) {
                    return it
                }
            }
            return null
        } else {
            val tvShow = getTvShowFromDB(id)
            if (tvShow != null) {
                tvShowCacheDataSource.saveTvShowToCache(tvShow)
                return tvShow
            }
            return null
        }
    }
}

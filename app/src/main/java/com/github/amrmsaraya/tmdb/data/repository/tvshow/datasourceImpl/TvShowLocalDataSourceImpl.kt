package com.github.amrmsaraya.tmdb.data.repository.tvshow.datasourceImpl

import com.github.amrmsaraya.tmdb.data.local.TvShowDao
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShow
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShowList
import com.github.amrmsaraya.tmdb.data.repository.tvshow.datasource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowLocalDataSourceImpl(private val tvShowDao: TvShowDao) : TvShowLocalDataSource {
    override suspend fun getTvShowsFromDB(category: String): TvShowList {
        return tvShowDao.getTvShowList(category)
    }

    override suspend fun getTvShowFromDB(id: Int): TvShow {
        return tvShowDao.getTvShow(id)
    }

    override suspend fun insertTvShowsToDB(tvShows: TvShowList) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.insertTvShowList(tvShows)
        }
    }

    override suspend fun insertTvShowToDB(tvShow: TvShow) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.insertTvShow(tvShow)
        }
    }
}

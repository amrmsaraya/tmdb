package com.github.amrmsaraya.tmdb.domain.repository

import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShow
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShowList

interface TvShowRepo {
    suspend fun getTvShow(id: Int): TvShow?
    suspend fun getTvShows(category: String): TvShowList?
    suspend fun updateTvShow(id: Int): TvShow?
    suspend fun updateTvShows(category: String): TvShowList?
}

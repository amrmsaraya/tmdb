package com.github.amrmsaraya.tmdb.data.repository.tvshow.datasource

import com.github.amrmsaraya.tmdb.data.model.cast.CastList
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShow
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShows(category: String): Response<TvShowList>
    suspend fun getTvShow(id: Int): Response<TvShow>
    suspend fun getCast(id: Int): Response<CastList>
}

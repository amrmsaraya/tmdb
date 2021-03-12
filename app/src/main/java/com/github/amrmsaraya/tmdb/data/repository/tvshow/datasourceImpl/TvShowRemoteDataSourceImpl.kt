package com.github.amrmsaraya.tmdb.data.repository.tvshow.datasourceImpl

import com.github.amrmsaraya.tmdb.data.model.cast.CastList
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShow
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShowList
import com.github.amrmsaraya.tmdb.data.remote.TMDBService
import com.github.amrmsaraya.tmdb.data.repository.tvshow.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(private val tmdbService: TMDBService, private val apiKey: String) :
    TvShowRemoteDataSource {
    override suspend fun getTvShows(category: String): Response<TvShowList> {
        return tmdbService.getTvShows(category, apiKey)
    }

    override suspend fun getCast(id: Int): Response<CastList> {
        return tmdbService.getTvShowCast(id, apiKey)
    }

    override suspend fun getTvShow(id: Int): Response<TvShow> {
        return tmdbService.getTvShow(id, apiKey)
    }
}

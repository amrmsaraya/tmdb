package com.github.amrmsaraya.tmdb.domain.usecase.tvshow

import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShowList
import com.github.amrmsaraya.tmdb.domain.repository.TvShowRepo

class UpdateTvShowsUseCase(private val tvShowRepo: TvShowRepo) {
    suspend fun execute(category: String): TvShowList? = tvShowRepo.updateTvShows(category)
}

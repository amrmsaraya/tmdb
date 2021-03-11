package com.github.amrmsaraya.tmdb.domain.usecase.tvshow

import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShow
import com.github.amrmsaraya.tmdb.domain.repository.TvShowRepo

class UpdateTvShowUseCase(private val tvShowRepo: TvShowRepo) {
    suspend fun execute(id: Int): TvShow? = tvShowRepo.updateTvShow(id)
}

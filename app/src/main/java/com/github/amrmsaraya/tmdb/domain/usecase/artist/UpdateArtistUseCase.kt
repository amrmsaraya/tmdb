package com.github.amrmsaraya.tmdb.domain.usecase.artist

import com.github.amrmsaraya.tmdb.data.model.artist.Artist
import com.github.amrmsaraya.tmdb.domain.repository.ArtistRepo

class UpdateArtistUseCase(private val artistRepo: ArtistRepo) {
    suspend fun execute(id: Int): Artist? = artistRepo.updateArtist(id)
}

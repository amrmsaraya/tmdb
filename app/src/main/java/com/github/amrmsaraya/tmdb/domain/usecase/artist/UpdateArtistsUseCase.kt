package com.github.amrmsaraya.tmdb.domain.usecase.artist

import com.github.amrmsaraya.tmdb.data.model.artist.ArtistList
import com.github.amrmsaraya.tmdb.domain.repository.ArtistRepo

class UpdateArtistsUseCase(private val artistRepo: ArtistRepo) {
    suspend fun execute(): ArtistList? = artistRepo.updateArtists()
}

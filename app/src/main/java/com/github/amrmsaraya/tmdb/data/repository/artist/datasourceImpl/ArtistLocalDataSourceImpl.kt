package com.github.amrmsaraya.tmdb.data.repository.artist.datasourceImpl

import com.github.amrmsaraya.tmdb.data.local.ArtistDao
import com.github.amrmsaraya.tmdb.data.model.artist.Artist
import com.github.amrmsaraya.tmdb.data.model.artist.ArtistList
import com.github.amrmsaraya.tmdb.data.repository.artist.datasource.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl(private val artistDao: ArtistDao) : ArtistLocalDataSource {
    override suspend fun getArtistsFromDB(): ArtistList {
        return artistDao.getArtistList()
    }

    override suspend fun getArtistFromDB(id: Int): Artist {
        return artistDao.getArtist(id)
    }

    override suspend fun insertArtistsToDB(artists: ArtistList) {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.insertArtistList(artists)
        }
    }

    override suspend fun insertArtistToDB(artist: Artist) {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.insertArtist(artist)
        }
    }
}

package com.github.amrmsaraya.tmdb.data.repository.artist

import android.util.Log
import com.github.amrmsaraya.tmdb.data.model.artist.Artist
import com.github.amrmsaraya.tmdb.data.model.artist.ArtistList
import com.github.amrmsaraya.tmdb.data.repository.artist.datasource.ArtistCacheDataSource
import com.github.amrmsaraya.tmdb.data.repository.artist.datasource.ArtistLocalDataSource
import com.github.amrmsaraya.tmdb.data.repository.artist.datasource.ArtistRemoteDataSource
import com.github.amrmsaraya.tmdb.domain.repository.ArtistRepo

class ArtistRepoImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
) : ArtistRepo {
    override suspend fun getArtist(id: Int): Artist? {
        TODO("Not yet implemented")
    }

    override suspend fun getArtists(): ArtistList? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtist(id: Int): Artist? {
        TODO("Not yet implemented")
    }

    override suspend fun updateArtists(): ArtistList? {
        val artistList = getArtistsFromAPI()
        if (artistList != null) {
            artistLocalDataSource.insertArtistsToDB(artistList)
            artistCacheDataSource.saveArtistsToCache(artistList)
        }
        return artistList
    }

    suspend fun getArtistsFromAPI(): ArtistList? {
        return try {
            val response = artistRemoteDataSource.getArtists()
            response.body()
        } catch (exception: Exception) {
            Log.i("myTag", exception.message.toString())
            null
        }
    }

    suspend fun getArtistsFromDB(): ArtistList? {
        var artistList: ArtistList? = null
        try {
            artistList = artistLocalDataSource.getArtistsFromDB()
        } catch (exception: Exception) {
            Log.i("myTag", exception.message.toString())
        }
        if (artistList != null) {
            return artistList
        } else {
            artistList = getArtistsFromAPI()
            if (artistList != null) {
                artistLocalDataSource.insertArtistsToDB(artistList)
            }
        }
        return artistList
    }

    suspend fun getArtistsFromCache(): ArtistList? {
        var artistList: ArtistList? = null
        try {
            artistList = artistCacheDataSource.getArtistsFromCache()
        } catch (exception: Exception) {
            Log.i("myTag", exception.message.toString())
        }
        if (artistList != null) {
            return artistList
        } else {
            artistList = getArtistsFromDB()
            if (artistList != null) {
                artistCacheDataSource.saveArtistsToCache(artistList)
            }
        }
        return artistList
    }
}

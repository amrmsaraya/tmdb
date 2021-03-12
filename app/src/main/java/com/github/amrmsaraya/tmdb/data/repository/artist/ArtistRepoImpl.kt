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
        return getArtistFromCache(id)
    }

    override suspend fun getArtists(): ArtistList? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtist(id: Int): Artist? {
        val artist = getArtistFromAPI(id)
        if (artist != null) {
            artistLocalDataSource.insertArtistToDB(artist)
            artistCacheDataSource.saveArtistToCache(artist)
        }
        return artist
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

    suspend fun getArtistFromAPI(id: Int): Artist? {
        return try {
            val response = artistRemoteDataSource.getArtist(id)
            val body = response.body()
            if (body != null) {
                val creditResponse = artistRemoteDataSource.getCredit(id)
                if (creditResponse.body() != null) {
                    body.credit = creditResponse.body()
                }
            }
            body
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

    suspend fun getArtistFromDB(id: Int): Artist? {
        var artist: Artist? = null
        try {
            artist = artistLocalDataSource.getArtistFromDB(id)
        } catch (exception: Exception) {
            Log.i("myTag", exception.message.toString())
        }
        if (artist != null) {
            return artist
        } else {
            artist = getArtistFromAPI(id)
            if (artist != null) {
                artistLocalDataSource.insertArtistToDB(artist)
            }
        }
        return artist
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

    suspend fun getArtistFromCache(id: Int): Artist? {
        var artistList = emptyList<Artist>()
        try {
            artistList = artistCacheDataSource.getArtistFromCache()
        } catch (exception: Exception) {
            Log.i("myTag", exception.message.toString())
        }
        if (artistList.isNotEmpty()) {
            artistList.forEach {
                if (it.id == id) {
                    return it
                }
            }
            return null
        } else {
            val artist = getArtistFromDB(id)
            if (artist != null) {
                artistCacheDataSource.saveArtistToCache(artist)
                return artist
            }
            return null
        }
    }
}

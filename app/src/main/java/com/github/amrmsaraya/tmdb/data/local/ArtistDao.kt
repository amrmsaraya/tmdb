package com.github.amrmsaraya.tmdb.data.local

import androidx.room.*
import com.github.amrmsaraya.tmdb.data.model.artist.Artist
import com.github.amrmsaraya.tmdb.data.model.artist.ArtistList

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: Artist)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtistList(artists: ArtistList)

    @Update
    suspend fun updateArtist(artist: Artist)

    @Query("DELETE FROM artist ")
    suspend fun deleteAllArtist()

    @Query("SELECT * FROM artist WHERE id=:id")
    suspend fun getArtist(id: Int): Artist

    @Query("SELECT * FROM artist_list")
    suspend fun getArtistList(): ArtistList
}

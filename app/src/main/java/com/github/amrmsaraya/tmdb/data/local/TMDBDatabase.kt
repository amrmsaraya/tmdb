package com.github.amrmsaraya.tmdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.amrmsaraya.tmdb.data.model.artist.Artist
import com.github.amrmsaraya.tmdb.data.model.artist.ArtistList
import com.github.amrmsaraya.tmdb.data.model.movie.Movie
import com.github.amrmsaraya.tmdb.data.model.movie.MovieList
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShow
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShowList
import com.github.amrmsaraya.tmdb.utils.Converters

@Database(
    entities = [Movie::class, MovieList::class, TvShow::class, TvShowList::class, Artist::class, ArtistList::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TMDBDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun artistDao(): ArtistDao
}

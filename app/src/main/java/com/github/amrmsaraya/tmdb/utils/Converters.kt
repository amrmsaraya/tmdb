package com.github.amrmsaraya.tmdb.utils

import androidx.room.TypeConverter
import com.github.amrmsaraya.tmdb.data.model.artist.Artist
import com.github.amrmsaraya.tmdb.data.model.artist.Credit
import com.github.amrmsaraya.tmdb.data.model.cast.CastList
import com.github.amrmsaraya.tmdb.data.model.movie.Movie
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShow
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromMovie(movies: List<Movie>): String {
        return Gson().toJson(movies)
    }

    @TypeConverter
    fun toMovies(string: String): List<Movie> {
        val type = object : TypeToken<List<Movie>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromTvShow(tvShows: List<TvShow>): String {
        return Gson().toJson(tvShows)
    }

    @TypeConverter
    fun toTvShow(string: String): List<TvShow> {
        val type = object : TypeToken<List<TvShow>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromArtist(artist: List<Artist>): String? {
        return Gson().toJson(artist)
    }

    @TypeConverter
    fun toArtist(string: String): List<Artist> {
        val type = object : TypeToken<List<Artist>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromGenreIds(genreIds: List<Int>): String? {
        return Gson().toJson(genreIds)
    }

    @TypeConverter
    fun toGenreIds(string: String): List<Int> {
        val type = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromAlsoKnownAs(alsoKnownAs: List<String>): String? {
        return Gson().toJson(alsoKnownAs)
    }

    @TypeConverter
    fun toAlsoKnownAs(string: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromCastList(cast: CastList?): String? {
        return Gson().toJson(cast)
    }

    @TypeConverter
    fun toCastList(string: String): CastList? {
        return Gson().fromJson(string, CastList::class.java)
    }

    @TypeConverter
    fun fromCredit(cast: Credit?): String? {
        return Gson().toJson(cast)
    }

    @TypeConverter
    fun toCredit(string: String): Credit? {
        return Gson().fromJson(string, Credit::class.java)
    }

}

package com.github.amrmsaraya.tmdb.data.model.tvshow


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tvShow_list")
data class TvShowList(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val tvShows: List<TvShow>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @PrimaryKey
    var category: String
)

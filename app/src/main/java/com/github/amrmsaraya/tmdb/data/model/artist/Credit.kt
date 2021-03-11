package com.github.amrmsaraya.tmdb.data.model.artist


import com.google.gson.annotations.SerializedName

data class Credit(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val id: Int
)

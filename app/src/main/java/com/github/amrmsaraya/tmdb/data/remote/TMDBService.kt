package com.github.amrmsaraya.tmdb.data.remote


import com.github.amrmsaraya.tmdb.data.model.artist.Artist
import com.github.amrmsaraya.tmdb.data.model.artist.ArtistList
import com.github.amrmsaraya.tmdb.data.model.artist.Credit
import com.github.amrmsaraya.tmdb.data.model.cast.CastList
import com.github.amrmsaraya.tmdb.data.model.movie.Movie
import com.github.amrmsaraya.tmdb.data.model.movie.MovieList
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShow
import com.github.amrmsaraya.tmdb.data.model.tvshow.TvShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {

    @GET("/movie/{type}")
    suspend fun getMovies(
        @Path("type") type: String,
        @Query("api_key") apiKey: String
    ): Response<MovieList>

    @GET("/movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<Movie>

    @GET("/tv/{type}")
    suspend fun getTvShows(
        @Path("type") type: String,
        @Query("api_key") apiKey: String
    ): Response<TvShowList>

    @GET("/tv/{id}")
    suspend fun getTvShow(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<TvShow>

    @GET("/person/popular")
    suspend fun getPopularArtists(@Query("api_key") apiKey: String): Response<ArtistList>

    @GET("/person/{id}")
    suspend fun getArtist(@Path("id") id: Int, @Query("api_key") apiKey: String): Response<Artist>

    @GET("/person/{id}/combined_credits")
    suspend fun getCredits(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<Credit>

    @GET("/movie/{id}/credits")
    suspend fun getMovieCast(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<CastList>

    @GET("/tv/{id}/credits")
    suspend fun getTvShowCast(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<CastList>
}

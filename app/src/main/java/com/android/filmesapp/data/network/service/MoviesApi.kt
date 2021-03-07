package com.android.filmesapp.data.network.service

import com.android.filmesapp.data.network.response.ApiDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = 2
    ): ApiDataResponse

    @GET("3/movie/popular")
    suspend fun getAllMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("page") page: Int = 1
    ): ApiDataResponse

    @GET("3/search/movie?")
    fun getSearchFilms(
        @Query("api_key") api_key: String = API_KEY,
        @Query("query") query: String
    ): Call<ApiDataResponse>

}
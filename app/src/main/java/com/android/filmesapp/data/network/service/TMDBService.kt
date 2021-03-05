package com.carlos.marvel.data.network.service

import com.carlos.marvel.data.network.response.ApiDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("3/movie/popular")
    fun getFilms(
        @Query("api_key") api_key: String = API_KEY,
        @Query("page") page: Int = 1
    ): Call<ApiDataResponse>

    @GET("3/search/movie?")
    fun getSearchFilms(
        @Query("api_key") api_key: String = API_KEY,
        @Query("query") query: String = "Wonder Woman 1984"
    ): Call<ApiDataResponse>

}
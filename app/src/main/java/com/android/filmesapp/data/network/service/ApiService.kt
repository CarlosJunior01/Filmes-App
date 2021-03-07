package com.android.filmesapp.data.network.service

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {

   private const val BASE_URL = "https://api.themoviedb.org/"

    private fun initRetrofit() : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    val service: MoviesApi = initRetrofit().create(MoviesApi::class.java)
}




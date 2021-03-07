package com.android.filmesapp.data.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultsResponse (
    val backdrop_path:     String,
    val id:                Int,
    val original_language: String,
    val overview:          String,
    val poster_path:       String,
    val release_date:      String,
    val title:             String
)
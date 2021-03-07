package com.android.filmesapp.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiDataResponse (
    @Json(name = "results")
    val results : List<ResultsResponse>
)
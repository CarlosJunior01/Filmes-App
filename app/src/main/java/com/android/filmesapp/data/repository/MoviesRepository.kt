package com.android.filmesapp.data.repository

import com.android.filmesapp.data.model.Movies

interface MoviesRepository {
    suspend fun getMoviesList() : List<Movies>
}
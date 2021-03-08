package com.android.filmesapp.data.repository

import com.android.filmesapp.data.model.Movies
import com.android.filmesapp.data.network.service.MoviesApi

class MoviesRepositoryImp(private val service: MoviesApi) : MoviesRepository {

    override suspend fun getMoviesList(): List<Movies> {

        val list = mutableListOf<Movies>()
        val limitMovies = 19

        val moviesType = service.getTopRatedMovies()
        for (index: Int in 0..limitMovies) {
        val movies = Movies(
            id = moviesType.results[index].id,
            title = moviesType.results[index].title,
            overview = moviesType.results[index].overview,
            poster_path = moviesType.results[index].poster_path,
            backdrop_path = moviesType.results[index].backdrop_path,
            original_language = moviesType.results[index].original_language,
            release_date = moviesType.results[index].release_date
        )

        list.add(movies)
        }
        return list.toList()
    }
}
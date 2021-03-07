package com.android.filmesapp.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.filmesapp.data.network.service.ApiService
import com.android.filmesapp.data.repository.MoviesRepositoryImp

class MoviesViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val moviesRepository = MoviesRepositoryImp(ApiService.service)
        return FilmsViewModel(moviesRepository) as T
    }
}
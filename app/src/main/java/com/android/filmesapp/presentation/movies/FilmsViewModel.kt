package com.android.filmesapp.presentation.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.filmesapp.data.model.Movies
import com.android.filmesapp.data.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val moviesRepository: MoviesRepository, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {
    private val scope = CoroutineScope(dispatcher)
    val mutableLiveData: MutableLiveData<List<Movies>> = MutableLiveData()

    fun getFilms() {
        scope.launch {
            try {
                val response = moviesRepository.getMoviesList()
                mutableLiveData.postValue(response)

            } catch (e: Exception) {
            }
        }
    }
}











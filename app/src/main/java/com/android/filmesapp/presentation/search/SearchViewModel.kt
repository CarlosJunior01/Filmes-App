package com.android.filmesapp.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.filmesapp.data.network.service.ApiService
import com.android.filmesapp.data.network.service.MoviesApi
import com.android.filmesapp.data.network.response.ApiDataResponse
import com.android.filmesapp.data.network.response.ResultsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

        val mutableLiveData = MutableLiveData<List<ResultsResponse>>()
        val filmesLiveData: LiveData<List<ResultsResponse>> = mutableLiveData

        fun getFilms(search: String) {
            ApiService.service.getSearchFilms(query = search).enqueue(object :
                Callback<ApiDataResponse> {

                override fun onResponse(
                    call: Call<ApiDataResponse>,
                    response: Response<ApiDataResponse>
                ) {
                    if (response.isSuccessful) {
                        val listFilm: MutableList<MoviesApi> = mutableListOf()

                        response.body()?.let { response ->
                            mutableLiveData.value = response.results
                        }
                    } else {
                        Log.e("Erro API", response.message())
                    }
                }

                override fun onFailure(call: Call<ApiDataResponse>, t: Throwable) {
                    Log.e("Erro API ", t.message.toString())
                }
            })
        }
    }
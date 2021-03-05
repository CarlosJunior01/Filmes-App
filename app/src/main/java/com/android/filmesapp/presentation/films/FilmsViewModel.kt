package com.android.filmesapp.presentation.films

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.filmesapp.data.model.FilmsApi
import com.carlos.marvel.data.network.response.ApiDataResponse
import com.carlos.marvel.data.network.response.ResultsResponse
import com.carlos.marvel.data.network.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FilmsViewModel :ViewModel() {

    val mutableLiveData = MutableLiveData<List<ResultsResponse>>()
    val filmesLiveData : LiveData<List<ResultsResponse>> = mutableLiveData

    fun getFilms(){
        ApiService.service.getFilms(/*page = 2*/).enqueue(object : Callback<ApiDataResponse> {

            override fun onResponse(call: Call<ApiDataResponse>, response: Response<ApiDataResponse>) {
                if (response.isSuccessful){
                    val listFilm: MutableList<FilmsApi> = mutableListOf()

                    response.body()?.let {response ->
                        mutableLiveData.value = response.results
                    }
                }else{
                    Log.e("Erro API", response.message())
                }
            }
            override fun onFailure(call: Call<ApiDataResponse>, t: Throwable) {
                Log.e("Erro API ", t.message.toString())
            }
        })
    }
}
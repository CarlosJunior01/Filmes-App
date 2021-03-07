package com.android.filmesapp.presentation.player

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.filmesapp.data.model.FirebaseManager

class PlayerViewModel: ViewModel(){

    private var mutableMoviePosition = MutableLiveData<String>()
    var mMoviePosition = mutableMoviePosition

    fun getMovie(position: Int?){
        val video = FirebaseManager
        var videoPosition = video.video_01

        when(position){
            0 -> videoPosition  = video.video_01
            1 -> videoPosition  = video.video_02
            2 -> videoPosition  = video.video_03
            3 -> videoPosition  = video.video_04
            4 -> videoPosition  = video.video_05
            5 -> videoPosition  = video.video_06
            6 -> videoPosition  = video.video_07
            7 -> videoPosition  = video.video_08
            8 -> videoPosition  = video.video_09
            9 -> videoPosition  = video.video_10
            10 -> videoPosition = video.video_11
            11 -> videoPosition = video.video_12
        }
        mutableMoviePosition.value = videoPosition
    }
}
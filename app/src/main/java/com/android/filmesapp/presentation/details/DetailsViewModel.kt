package com.android.filmesapp.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.filmesapp.data.model.FirebaseManager

class DetailsViewModel: ViewModel(){

    private var mutableCoverPosition = MutableLiveData<String>()
    var mCoverPosition = mutableCoverPosition

    fun getCover(position: Int?){
        val cover = FirebaseManager
        var coverPosition = cover.cover_01

        when(position){
            0 -> coverPosition = cover.cover_01
            1 -> coverPosition = cover.cover_02
            2 -> coverPosition = cover.cover_03
            3 -> coverPosition = cover.cover_04
            4 -> coverPosition = cover.cover_05
            5 -> coverPosition = cover.cover_06
            6 -> coverPosition = cover.cover_07
            7 -> coverPosition = cover.cover_08
            8 -> coverPosition = cover.cover_09
            9 -> coverPosition = cover.cover_10
            10 -> coverPosition = cover.cover_11
            11 -> coverPosition = cover.cover_12
        }
        mutableCoverPosition.value = coverPosition
    }
}
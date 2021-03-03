package com.android.filmesapp.presentation.intros

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.filmesapp.R
import com.android.filmesapp.presentation.MainActivity
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide

class SlideActivity : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenMode()

        isButtonBackVisible = false
        isButtonNextVisible = false

        addSlide(
            FragmentSlide.Builder()
                .background(android.R.color.background_dark)
                .fragment(R.layout.intro_01)
                .build()

        )

        addSlide(
            FragmentSlide.Builder()
                .background(android.R.color.holo_red_dark)
                .fragment(R.layout.intro_02)
                .build()
        )

        addSlide(
            FragmentSlide.Builder()
                .background(android.R.color.holo_blue_dark)
                .fragment(R.layout.intro_03)
                .canGoForward(false)
                .build()
        )

    }

    fun openNextActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun screenMode() {
        supportActionBar!!.hide()
    }
}
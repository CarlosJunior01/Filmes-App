package com.android.filmesapp.presentation.intros

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.filmesapp.R
import com.android.filmesapp.presentation.authentication.FormLoginActivity
import com.android.filmesapp.presentation.films.FilmsActivity
import com.google.firebase.auth.FirebaseAuth
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide

class SlideActivity : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenMode()
        VerifyUserLogin()

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
        val intent = Intent(this, FormLoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun screenMode() {
        supportActionBar!!.hide()
    }

    private fun VerifyUserLogin(){
        val userLogin = FirebaseAuth.getInstance().currentUser

        if(userLogin != null){
            OpenFilmesActivity()
        }
    }

    private fun OpenFilmesActivity(){
        var intent = Intent(this, FilmsActivity::class.java)
        startActivity(intent)
    }
}
package com.android.filmesapp.presentation.intros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import com.android.filmesapp.R
import com.android.filmesapp.presentation.MainActivity

@Suppress("DEPRECATION")
class IntroLoadingActivity : AppCompatActivity() {

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_loading)
        fullscreenMode()

        Handler().postDelayed({
            openLoginActivity()
        },3000)
    }

    private fun openLoginActivity(){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun fullscreenMode() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        supportActionBar!!.hide()
    }
}
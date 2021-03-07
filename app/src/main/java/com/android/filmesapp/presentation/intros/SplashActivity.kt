package com.android.filmesapp.presentation.intros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.android.filmesapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    companion object{
        const val TRES_SEGUNDOS = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_loading)
        fullscreenMode()

        CoroutineScope(Dispatchers.Main).launch {
            delay(TRES_SEGUNDOS)
            openLoginActivity()
        }
    }

    private fun openLoginActivity(){
        val intent = Intent(this, SlideActivity::class.java)
        startActivity(intent)
        finish()
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
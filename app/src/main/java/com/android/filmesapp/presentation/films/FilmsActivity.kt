package com.android.filmesapp.presentation.films

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.android.filmesapp.R
import com.android.filmesapp.databinding.ActivityFilmsBinding
import com.android.filmesapp.databinding.ActivityFormLoginBinding
import com.android.filmesapp.presentation.authentication.FormLoginActivity
import com.google.firebase.auth.FirebaseAuth

class FilmsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilmsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmsBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.signout -> {
                FirebaseAuth.getInstance().signOut()
                OpenLoginActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun OpenLoginActivity(){
        var intent = Intent(this, FormLoginActivity::class.java)
        startActivity(intent)
    }


}



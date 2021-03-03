package com.android.filmesapp.presentation.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.filmesapp.databinding.ActivityFormLoginBinding

class FormLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtCadastrar.setOnClickListener {
            openCadastroActivity()
        }

    }

    private fun openCadastroActivity(){
        var intent = Intent(this, FormCadastroActivity::class.java)
        startActivity(intent)
    }
}

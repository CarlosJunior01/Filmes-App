package com.android.filmesapp.presentation.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.filmesapp.R
import com.android.filmesapp.databinding.ActivityFormLoginBinding
import com.android.filmesapp.presentation.movies.FilmsActivity
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class FormLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        verifyUserLogin()

        binding.btnEntrar.setOnClickListener {
            loginUser()
        }

        binding.txtCadastrar.setOnClickListener {
            openRegisterActivity()
        }

    }

    private fun verifyUserLogin(){
        val userLogin = FirebaseAuth.getInstance().currentUser

        if(userLogin != null){
            openMoviesActivity()
        }
    }

    private fun openRegisterActivity(){
        val intent = Intent(this, FormRegisterActivity::class.java)
        startActivity(intent)
    }

    private fun openMoviesActivity(){
        val intent = Intent(this, FilmsActivity::class.java)
        startActivity(intent)
    }

    private fun loginUser(){
        val email = binding.edtEmail.text.toString()
        val password = binding.edtSenha.text.toString()
        val message = binding.mensagem

        if(email.isEmpty() || password.isEmpty()){
            message.text = getString(R.string.preenchaTodosCampos)
        }else{
            authenticateUser()
        }
    }

    private fun authenticateUser () {
        val email = binding.edtEmail.text.toString()
        val password = binding.edtSenha.text.toString()
        val message = binding.mensagem

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful){
                Toast.makeText(this, getString(R.string.loginEfetuadoSucesso), Toast.LENGTH_SHORT).show()
                openMoviesActivity()
            }
        }.addOnFailureListener{
            val error = it

            when (error) {
                is FirebaseAuthInvalidCredentialsException -> message.text = getString(R.string.emailSenhaIncorretos)
                is FirebaseNetworkException -> message.text = getString(R.string.semConexaoInternet)
                else -> message.text = getString(R.string.erroLogarUsuario)
            }
        }
    }
}

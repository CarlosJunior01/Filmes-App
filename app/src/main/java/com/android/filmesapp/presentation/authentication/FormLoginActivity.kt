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

        VerifyUserLogin()

        binding.btnEntrar.setOnClickListener {
            LoginUser()
        }

        binding.txtCadastrar.setOnClickListener {
            OpenRegisterActivity()
        }

    }

    private fun VerifyUserLogin(){
        val userLogin = FirebaseAuth.getInstance().currentUser

        if(userLogin != null){
            OpenFilmesActivity()
        }
    }

    private fun OpenRegisterActivity(){
        val intent = Intent(this, FormRegisterActivity::class.java)
        startActivity(intent)
    }

    private fun OpenFilmesActivity(){
        val intent = Intent(this, FilmsActivity::class.java)
        startActivity(intent)
    }

    private fun LoginUser(){
        val email = binding.edtEmail.text.toString()
        val password = binding.edtSenha.text.toString()
        val message = binding.mensagem

        if(email.isEmpty() || password.isEmpty()){
            message.setText(getString(R.string.preenchaTodosCampos))
        }else{
            AuthenticateUser()
        }
    }

    private fun AuthenticateUser () {
        val email = binding.edtEmail.text.toString()
        val password = binding.edtSenha.text.toString()
        val message = binding.mensagem

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful){
                Toast.makeText(this, getString(R.string.loginEfetuadoSucesso), Toast.LENGTH_SHORT).show()
                OpenFilmesActivity()
            }
        }.addOnFailureListener{
            val error = it

            when{
                error is FirebaseAuthInvalidCredentialsException ->  message.setText(getString(R.string.emailSenhaIncorretos))
                error is FirebaseNetworkException ->  message.setText(getString(R.string.semConexaoInternet))
                else ->  message.setText(getString(R.string.erroLogarUsuario))
            }
        }
    }
}

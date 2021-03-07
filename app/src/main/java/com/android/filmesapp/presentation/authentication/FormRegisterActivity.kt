package com.android.filmesapp.presentation.authentication

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.android.filmesapp.R
import com.android.filmesapp.databinding.ActivityFormRegisterBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*

class FormRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {

            binding.edtEmail
            CadastrarUsuario()

        }
    }

    private fun CadastrarUsuario(){
        val email = binding.edtEmail.text.toString()
        val senha = binding.edtSenha.text.toString()
        val message = binding.mensagem

        if(email.isEmpty() || senha.isEmpty()){
            message.setText(getString(R.string.coloqueEmailSenha))
        } else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Cadastro Realizado Com Sucesso!", Toast.LENGTH_SHORT).show()
                    openLoginActivity()
                }
            }.addOnFailureListener {
                val error = it

                when{
                    error is FirebaseAuthWeakPasswordException ->  message.setText(getString(R.string.passwordException))
                    error is FirebaseAuthUserCollisionException ->  message.setText(getString(R.string.collisionException))
                    error is FirebaseNetworkException ->  message.setText(getString(R.string.networkException))
                    error is FirebaseAuthInvalidCredentialsException ->  message.setText(getString(R.string.invalidCredentialsException))
                    else ->  message.setText(getString(R.string.erroCadastrar))
                }
            }
        }
        hideKeyboard()
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    private fun openLoginActivity(){
        val intent = Intent(this, FormLoginActivity::class.java)
        startActivity(intent)
    }

}
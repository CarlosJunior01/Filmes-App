package com.android.filmesapp.presentation.authentication

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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
        var email = binding.edtEmail.text.toString()
        var senha = binding.edtSenha.text.toString()
        var message = binding.mensagem

        if(email.isEmpty() || senha.isEmpty()){
            message.setText("Coloque o seu e-mail e sua senha!")
        } else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Cadastro Realizado Com Sucesso!", Toast.LENGTH_SHORT).show()
                    openLoginActivity()
                }
            }.addOnFailureListener {
                var error = it

                when{
                    error is FirebaseAuthWeakPasswordException ->  message.setText("Insira uma senha com no mínimo 6 caracteres!")
                    error is FirebaseAuthUserCollisionException ->  message.setText("Email informado já possui cadastrado!")
                    error is FirebaseNetworkException ->  message.setText("Sem conexão com a internet!")
                    error is FirebaseAuthInvalidCredentialsException ->  message.setText("Digite um e-mail válido!")
                    else ->  message.setText("Erro ao cadastrar usuário!")
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
        var intent = Intent(this, FormLoginActivity::class.java)
        startActivity(intent)
    }

}
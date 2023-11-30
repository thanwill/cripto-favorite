package com.app.criptofavorite.view

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.app.criptofavorite.R
import com.app.criptofavorite.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    // firebase auth
    private lateinit var auth: FirebaseAuth
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_login)
        setContentView(binding.root)
        // Initialize Firebase Auth
        auth = Firebase.auth

        var progressBar = binding.loading


        // Verifica se o usuário já está logado e redireciona para a página principal
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }

        binding.username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkFieldsForEmptyValues()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkFieldsForEmptyValues()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.login.setOnClickListener {

            var username = binding.username.text.toString()
            var password = binding.password.text.toString()

            println("username: $username")
            println("password: $password")

            // verifica se os campos de usuário e senha estão preenchidos
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    baseContext,
                    "Preencha os campos de usuário e senha.",
                    Toast.LENGTH_SHORT,
                ).show()
                return@setOnClickListener
            }

            progressBar.visibility = View.VISIBLE

            // snackbar para mostrar o progresso da autenticação na cor verde
            val snackbar = Snackbar.make(
                binding.root,
                "Autenticando",
                Snackbar.LENGTH_INDEFINITE
            )



            snackbar.show()

            auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        updateUI(null)
                    }
                }

        }

        // Usuário deseja se cadastrar
        /*
        auth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }
        */

    }

    // Função para atualizar a interface do usuário
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            reload()
        }
    }

    // Função para recarregar a página principal
    private fun reload() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun checkFieldsForEmptyValues() {
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()

        binding.login.isEnabled = username.isNotEmpty() && password.isNotEmpty()


    }
}
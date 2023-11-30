package com.app.criptofavorite.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.app.criptofavorite.R
import com.app.criptofavorite.databinding.ActivityCadastroBinding
import com.app.criptofavorite.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class CadastroActivity : AppCompatActivity() {

    val db = Firebase.firestore
    private lateinit var binding: ActivityCadastroBinding
    private lateinit var usuario : User
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        val name = binding.nomeUsuario.text
        val email = binding.emailUsuario.text
        val password = binding.senhaUsuario.text
        val phone = binding.celularUsuario.text
        val newsletter = binding.checkboxWhatsappUsuario.isChecked



        binding.salvar.setOnClickListener {

            usuario = User(
                name = name.toString(),
                email = email.toString(),
                password = password.toString(),
                phone = phone.toString(),
                newsletter = newsletter
            )

            auth.createUserWithEmailAndPassword(email.toString(), password.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("Sucess", "createUserWithEmail:success")
                        val user = auth.currentUser

                    } else {
                        Log.w("Error", "createUserWithEmail:failure", task.exception)
                    }
                }

            println("Usuario: $usuario")

            db.collection("usuarios")
                .add(usuario)
                .addOnSuccessListener {document ->
                    binding.nomeUsuario.text.clear()
                    binding.emailUsuario.text.clear()
                    binding.senhaUsuario.text.clear()
                    binding.celularUsuario.text.clear()
                    binding.checkboxWhatsappUsuario.isChecked = false
                    Log.d("Sucess", "DocumentSnapshot added with ID: ${document.id}")

                    Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)



                }
                .addOnFailureListener {e ->
                    binding.nomeUsuario.text.clear()
                    binding.emailUsuario.text.clear()
                    binding.senhaUsuario.text.clear()
                    binding.celularUsuario.text.clear()
                    binding.checkboxWhatsappUsuario.isChecked = false
                    Log.w("Error", "Error adding document", e)
                    Toast.makeText(this, "Erro ao cadastrar usuário!", Toast.LENGTH_SHORT).show()
                }




        }

    }



}
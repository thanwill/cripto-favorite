package com.app.criptofavorite

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.app.criptofavorite.databinding.ActivityMainBinding
import com.app.criptofavorite.model.FinanceResponse
import com.app.criptofavorite.repository.remote.FinanceClient
import com.app.criptofavorite.repository.remote.FinanceService
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore

        // Create a new user with a first and last name
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

        val service = FinanceClient.createService() // Cria a instância do Retrofit
        val call = service.getFinance() // Chama o método para obter os dados

        call.enqueue(object : Callback<FinanceResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<FinanceResponse>, response: Response<FinanceResponse>) {
                if (response.isSuccessful) {
                    var financeResponse = response.body()

                    if(financeResponse != null){
                        Log.d(TAG, "onResponse: ${financeResponse.results}")
                    }
                } else {
                    // Tratamento para resposta não bem-sucedida
                }
            }

            override fun onFailure(call: Call<FinanceResponse>, t: Throwable) {
                val error = t.message
                // Tratamento de falha
            }
        })


        // navigation bar
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passa cada ID de menu como um conjunto de IDs, porque cada
        // menu deve ser considerado como destinos de nível superior.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
package com.app.criptofavorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.criptofavorite.databinding.ActivityMainBinding
import com.app.criptofavorite.model.Finance
import com.app.criptofavorite.repository.remote.FinanceClient
import com.app.criptofavorite.view.adapter.CurrencyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var adapter = CurrencyAdapter()

        val service = FinanceClient.createService()
        val call = service.getExchangeRates()
        call.enqueue(object : Callback<Finance> {
            override fun onResponse(
                call: Call<Finance>,
                response: Response<Finance>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    println("Moedas: ${body?.results?.currencies}")

                    if (body != null) {
                        adapter.addCurrency(body.results.currencies.USD)
                        adapter.addCurrency(body.results.currencies.EUR)
                        adapter.addCurrency(body.results.currencies.GBP)
                        adapter.addCurrency(body.results.currencies.ARS)
                        adapter.addCurrency(body.results.currencies.CAD)
                        adapter.addCurrency(body.results.currencies.AUD)
                        adapter.addCurrency(body.results.currencies.JPY)
                        adapter.addCurrency(body.results.currencies.CNY)
                        adapter.addCurrency(body.results.currencies.BTC)
                    }

                    binding.recyclerView.adapter = adapter // RecyclerView

                }
            }

            override fun onFailure(call: Call<Finance>, t: Throwable) {
                println("Falha na requisição: ${t.message}")
            }
        })

        /* navigation bar
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

        Código da XML

        <fragment
        android:id="@+id/nav_host_fragment_activity_main"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:defaultNavHost="true"
        app:layout_constraintBottom_toTopOf="@id/nav_view"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:navGraph="@navigation/mobile_navigation" />
         */
    }


}


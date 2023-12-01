package com.app.criptofavorite.view.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.criptofavorite.model.Currency
import com.app.criptofavorite.model.Finance
import com.app.criptofavorite.repository.remote.FinanceClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    // LiveData privado para manipulação interna
    private val _currencies = MutableLiveData<List<Currency>>()

    // LiveData público para observação pela UI
    val currencies: LiveData<List<Currency>> = _currencies

    // Cliente para chamar a API
    private val financeService = FinanceClient.createService()

    init {
        loadCurrencies()
    }

    private fun loadCurrencies() {
        financeService.getExchangeRates().enqueue(object : Callback<Finance> {
            override fun onResponse(call: Call<Finance>, response: Response<Finance>) {
                if (response.isSuccessful) {
                    response.body()?.let { finance ->
                        _currencies.value = listOf(
                            finance.results.currencies.USD,
                            finance.results.currencies.EUR,
                            finance.results.currencies.JPY,
                            finance.results.currencies.GBP,
                            finance.results.currencies.CAD,
                            finance.results.currencies.CNY,
                            finance.results.currencies.BTC,
                            finance.results.currencies.AUD,
                            finance.results.currencies.ARS
                        )
                    }
                } else {
                    // Tratar o caso de resposta não bem-sucedida
                }
            }

            override fun onFailure(call: Call<Finance>, t: Throwable) {
                // Tratar falhas na chamada da API
            }
        })
    }

}
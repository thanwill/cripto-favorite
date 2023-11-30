package com.app.criptofavorite.repository.remote

import com.app.criptofavorite.model.Finance
import retrofit2.Call
import retrofit2.http.GET

interface FinanceService {
    @GET("finance")
    fun getExchangeRates(): Call<Finance>
}

package com.app.criptofavorite.repository.remote

import com.app.criptofavorite.model.FinanceResponse
import retrofit2.Call
import retrofit2.http.GET

interface FinanceService {
    // Ombtem a cotação do dólar comercial através do endpoint /finance

    @GET("/finance")
    // realiza uma requisicao GET para a API e recebe um objeto do tipo FinanceResponse
    fun getFinance() : Call<FinanceResponse>
}

